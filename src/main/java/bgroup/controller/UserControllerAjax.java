package bgroup.controller;


import bgroup.mysql.model.PrintArchive;
import bgroup.mysql.service.PrintArchiveService;
import bgroup.mysql.service.SmsCodeService;
import bgroup.oracle.model.*;
import bgroup.service.AmountService;
import bgroup.service.ContractService;
import bgroup.service.HelpFioService;
import bgroup.service.ServDateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by VSB on 10.02.2017.
 * MiS
 */
@Controller
@EnableWebMvc
public class UserControllerAjax {
    static final Logger logger = LoggerFactory.getLogger(UserControllerAjax.class);

    @Autowired
    ContractService contractService;
    @Autowired
    AmountService amountService;
    @Autowired
    ServDateService servDateService;
    @Autowired
    HelpFioService helpFioService;
    @Autowired
    SmsCodeService smsCodeService;
    @Autowired
    PrintArchiveService printArchiveService;

    @ResponseBody
    @RequestMapping(value = "getContract", produces = {"text/plain; charset=UTF-8"})
    public String getContract(HttpServletRequest request) {
        String responseBody = "Error";
        CustomUser user = getCustomerUser();
        if (request != null) {
            //logger.info("getDog" + request.getParameter("year"));
            String[] dateFromTo = getDatFromTo(request);
            ServDate servDate = servDateService.getServDate(user.getKeyId(), dateFromTo[0], dateFromTo[1]);
            Contract contract = contractService.getDog(user.getKeyId());
            responseBody = getContractText(user, contract, servDate);
            PrintArchive printArchive = new PrintArchive(user, responseBody);

            Amount amount = null;
            try {
                amount = amountService.getAmount(user.getKeyId(), dateFromTo[0], dateFromTo[1]);
            } catch (Exception e) {
                logger.error(e.toString());
            }
            if (amount.getAMOUNT2() == null || amount.getAMOUNT2().equals("") || amount.getAMOUNT2().equals("  ( копeeк) ")) {
                return "Error: не было оказано платных услуг";
            }


            if (printArchive != null && printArchiveService.savePrintArchiveToDb(printArchive)) {
                logger.info("Save printForm " + printArchive.getId() + "to DB: ok");
            } else {
                logger.error("Не возможно сохранить форму в базу");
                responseBody = "Error";
            }
        }
        return responseBody;
    }

    @ResponseBody
    @RequestMapping(value = "checkCode", produces = {"text/plain; charset=UTF-8"})
    public String checkCode(HttpServletRequest request) {
        String responseBody = "-1";
        CustomUser user = getCustomerUser();
        if (smsCodeService.checkSmsCode(request, user)) {
            addRole();
            responseBody = "1";
        }
        return responseBody;
    }

    private boolean addRole() {
        CustomUser user = getCustomerUser();
        Role r = new Role();
        r.setName("ROLE_USER");
        List<Role> roles = user.getAuthorities();
        roles.add(r);
        user.setAuthorities(roles);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "getAmount", produces = {"text/plain; charset=UTF-8"})
    public String getAmount(HttpServletRequest request) {
        String responseBody = "Error";
        CustomUser user = getCustomerUser();
        if (request != null) {
            //logger.info("getDog" + request.getParameter("year"));
            String[] dateFromTo = getDatFromTo(request);
            String inn = request.getParameter("inn");
            if (inn == null) inn = "";
            if (dateFromTo == null) {
                return "Error: не выбран год";
            }
            logger.debug("dateFrom:" + dateFromTo[0]);
            Amount amount = null;
            try {
                amount = amountService.getAmount(user.getKeyId(), dateFromTo[0], dateFromTo[1]);
            } catch (Exception e) {
                logger.error(e.toString());
            }
            if (amount.getAMOUNT2() == null || amount.getAMOUNT2().equals("") || amount.getAMOUNT2().equals("  ( копeeк) ")) {
                return "Error: не было оказано платных услуг";
            }

            ServDate servDate = servDateService.getServDate(user.getKeyId(), dateFromTo[0], dateFromTo[1]);
            HelpFio helpFio = helpFioService.getHelpFio(user.getKeyId());
            if (user == null)
                logger.debug("User: is null");
            responseBody = getAmountText(user, amount, servDate, helpFio, inn);
            Contract contract = contractService.getDog(user.getKeyId());
            responseBody += getContractText(user, contract, servDate);
            //responseBody = getAmountText(user,null,null);
            //logger.debug(responseBody);
        }
        logger.debug("stop Ajax");
        return responseBody;
    }

    private String[] getDatFromTo(HttpServletRequest request) {
        String[] datFromTo = null;
        String yearString = request.getParameter("year");
        int year = -1;
        if (yearString != null) {
            try {
                year = Integer.parseInt(yearString);
            } catch (Exception e) {
                logger.error("Ошибка в выборе года:" + yearString);
            }
        }
        if (year != -1) {
            datFromTo = new String[2];
            datFromTo[0] = year + "-01-01 00:00:00";
            datFromTo[1] = (year + 1) + "-01-01 00:00:00";
        }
        return datFromTo;
    }

    private String getAmountText(CustomUser user, Amount amount, ServDate servDate, HelpFio helpFio, String inn) {
        String innText = null;
        String innText1 = null;
        if (helpFio.getSNILS() == null) innText = inn;
        else innText = helpFio.getSNILS();
        if (helpFio.getSNILS1() == null) innText1 = inn;
        else innText1 = helpFio.getSNILS1();
        String hi = "он";
        String his = "ему";
        String pay = "оплатил";
        if (user != null) {
            logger.debug("user is not null");
        }
        if (user.getSex() != null && user.getSex() == 1) {
            hi = "она";
            his = "ей";
            pay = "оплатила";
        }
        String[] strings = getEuromedString(servDate);
        String euromed = strings[0];
        String euromedShort = strings[1];
        return "<pre>\n" +
                "Приложение № 1\n" +
                "УТВЕРЖДЕНО\n" +
                "приказом Минздрава России\n" +
                "и МНС России от 25 июля 2001 г. № 289/БГ-3-04/256\n" +
                "К О Р Е Ш О К\n" +
                "к справке об оплате медицинских услуг для представления\n" +
                "в налоговые органы Российской Федерации №\t\n" +
                helpFio.getN() + "\n" +
                "Ф.И.О. налогоплательщика\t" + helpFio.getFIO() + "\n" +
                "\n" +
                "ИНН налогоплательщика\t" + innText + "\n" +
                "Ф.И.О. пациента\t" + helpFio.getFIO5() + "\t, код услуги  ___1_________\t\n" +
                "№ карты амбулаторного, стационарного больного\t" + helpFio.getPATNUM() + "\n" +
                "\n" +
                "Стоимость медицинских услуг\t" + amount.getAMOUNT() + "\n" +
                "\n" +
                "Дата оплаты \t" + servDate.getONEDATE() + "\tг.\tДата выдачи справки “\t" + helpFio.getGIVEDATE() + "\tг.\n" +
                "\n" +
                "Подпись лица, выдавшего справку\t\t Подпись получателя\t\n" +
                "\n" +
                "\n" +
                "Министерство здравоохранения Российской Федерации\n" +
                "       " + euromedShort + " \n" +
                " г. Омск,  ул.Съездовская, 29, корп.3\n" +
                "    \t  ИНН 5504248024 \n" +
                "наименование и адрес учреждения,                выдавшего справку, ИНН\n" +
                "Лицензия " + helpFio.getLicense() + "\n" +
                "\n" +
                "            лицензия №, дата выдачи лицензии\n" +
                "                на срок - бессрочно\n" +
                "\n" +
                "                       срок действия лицензии\n" +
                "Министерство здравоохранения   \n" +
                "\tОмской области\n" +
                "кем выдана лицензия\n" +
                "\n" +
                "С П Р А В К А\n" +
                "об оплате медицинских услуг для представления\n" +
                "в налоговые органы Российской Федерации №\t\n" +
                helpFio.getN2() + "\n" +
                "\n" +
                "от \t" + helpFio.getGIVEDATE2() + "\n" +
                "Выдана налогоплательщику (Ф.И.О.)\t" + helpFio.getFIO3() + "\n" +
                "\n" +
                "\n" +
                "ИНН налогоплательщика\t" + innText1 + "\n" +
                "В том, что " + hi + " " + pay + " медицинские услуги стоимостью:\n" +
                amount.getAMOUNT2() + "\n" +
                "(сумма прописью)\n" +
                "Код услуг:\t1\t\n" +
                //"оказанные: "+his+", супруге(у), сыну (дочери), матери (отцу) \t" + helpFio.getFIO4() + "\n" +
                "оказанные: " + his + " \t" + helpFio.getFIO4() + "\n" +
                //"(нужное подчеркнуть)\n" +
                "Дата оплаты \t" + servDate.getONEDATE2() + "\tг.\t\n" +
                "Фамилия, имя, отчество и должность лица, выдавшего справку\t- экономист\n" +
                "\n" +
                "№ телефона (\t3812\t)\t331-402\t[registrator]\n" +
                "\tкод\t\t\t\n" +
                "печать\t(подпись лица, выдавшего справку)\n</pre>";
    }

    private String[] getEuromedString(ServDate servDate) {
        String[] strings = new String[2];
        String string = servDate.getMinservdate();
        DateFormat format = new SimpleDateFormat("dd.mm.yyyy");
        Date date = null;
        Date date2 = null;
        try {
            date = format.parse(string);
            date2 = format.parse("20.08.2014");
        } catch (ParseException e) {
            //e.printStackTrace();
            return null;
        }
        if (date.before(date2)) {
            strings[0] = "Закрытое акционерное общество «Многопрофильный центр современной медицины «Евромед» (ЗАО «МЦСМ «Евромед»)";
            strings[1] = "ЗАО «МЦСМ «Евромед»";
        } else {
            strings[0] = "Общество с ограниченной ответственностью «Многопрофильный центр современной медицины «Евромед» (ООО «МЦСМ «Евромед»)";
            strings[1] = "ООО «МЦСМ «Евромед»";
        }
        System.out.println(date);
        return strings;
    }

    private String getContractText(CustomUser user, Contract contract, ServDate servDate) {
        String responseBody = null;
        //if (contract.get)
        String hi = "он";
        String his = "ему";
        String pay = "оплатил";
        String doing = "действующий";
        String[] strings = getEuromedString(servDate);
        String euromed = strings[0];
        String euromedShort = strings[1];
        logger.debug("SEX: {}", user.getSex());
        if (user.getSex() != null && user.getSex() == 1) {
            hi = "она";
            his = "ей";
            pay = "оплатила";
            doing = "действующая";
        }
        responseBody = "<style type=\"text/css\" media=\"print\">\n" +
                "div.pagebreak {\n" +
                "   \n" +
                "page-break-before: always;\n" +
                "}\n" +
                "@media print { /* Стиль для печати */\n" +
                "    body {\n" +
                "     font-family: Times, 'Times New Roman', serif; /* Шрифт с засечками */\n" +
                "    }\n" +
                "    h1, h2, p {\n" +
                "     color: #000; /* Черный цвет текста */\n" +
                "    }\n" +
                "}\n" +
                "</style>\n" +
                "\n" +
                "<div class=\"pagebreak\"/>" +
                "ДОГОВОР № " + contract.getPATNUM() + " \n" +
                "на оказание платных медицинских услуг\n" +
                "\n" +
                "г. Омск\t" + servDate.getMinservdate() + "\n" +
                "\t\n" +
                euromed + ",\n" +
                " именуемое в дальнейшем «Исполнитель», в лице медицинского \n" +
                "регистратора регистратуры " + contract.getREGISTRATOR() + ", действующего на основании доверенности " + contract.getDoverennost() + ", с одной стороны, \n" +
                "и " + contract.getFio3() + ", " + doing + " от собственного имени, \n" +
                "или " + doing + " через законного представителя (мать, отец, усыновитель, опекун, попечитель) ________________________________________________ \n" +
                "или " + doing + " через представителя _______________________________________________________________ по доверенности №_______________ от «_____» ______________________20___г., " +
                "удостоверенной _______________________________________________________________, далее Потребитель, Законный представитель Потребителя и представитель Потребителя именуемые в дальнейшем «Потребитель», " +
                "с другой стороны, совместно именуемые «Стороны», заключили настоящий договор (далее по тексту – Договор) о нижеследующем:\n" +
                "\n" +
                "1.\tВ соответствии с настоящим Договором Исполнитель обязуется оказывать по заданию Потребителя на возмездной основе медицинские услуги, отвечающие требованиям, предъявляемым к методам " +
                "диагностики, профилактики и лечения, разрешенным на территории РФ, а Потребитель обязуется своевременно оплачивать стоимость медицинских услуг, предоставляемых по настоящему Договору и " +
                "выполнять требования Исполнителя для целей обеспечения своевременного и качественного оказания медицинских услуг, включая сообщение необходимых для этого сведений.\n" +
                "2.\tПри исполнении настоящего Договора стороны руководствуются действующим законодательством РФ и действующими Правилами предоставления платных медицинских услуг в " + euromedShort + " (далее - Правила).\n" +
                "3.\tНаименование, вид медицинской услуги, срок ее оказания, сведения о лице, непосредственно оказывающем медицинскую услугу указываются в маршрутных листах Потребителя и/или кассовом чеке (чеке) об оплате соответствующей медицинской услуги (далее сопроводительные документы Потребителя), которые будут составлять неотъемлемую часть настоящего договора. \n" +
                "4.\tСвидетельством согласия Потребителя с условиями настоящего договора и с условиями предоставления медицинской услуги является осуществление Потребителем соответствующих действий, в том числе заказ услуг и (или) их оплата, предоставление информированного добровольного согласия на медицинское вмешательство, предоставление согласия с назначенным обследованием и лечением путем подписания соответствующей информационной графы на заключении врача (протоколе). \n" +
                "5.\tПосле исполнения договора (оказания соответствующей медицинской услуги) Исполнитель выдает Потребителю (законному представителю потребителя) медицинские документы (копии медицинских документов, выписки из медицинских документов), отражающие состояние его здоровья. Указанные медицинские документы также подтверждают факт предоставления Исполнителем Потребителю платной медицинской услуги и ее получение Потребителем. \n" +
                "6.\tПеречень медицинских услуг, предоставляемых Потребителю, определяется самим Потребителем в соответствии с действующим Прейскурантом Исполнителя в момент осуществления им оплаты соответствующей медицинской услуги и указывается в сопроводительных документах Потребителя.\n" +
                "7.\tСтоимость медицинских услуг, предоставляемых Потребителю Исполнителем по настоящему договору определяется на основании действующего Прейскуранта Исполнителя в момент заказа и оплаты соответствующей услуги.\n" +
                "8.\tОплата медицинских услуг по настоящему Договору производится Потребителем в полном объеме до получения Потребителем медицинской услуги, если иное не установлено дополнительным соглашением сторон.\n" +
                "9.\tИсполнитель оказывает услуги по настоящему Договору в помещениях Исполнителя по адресу: 644024, г. Омск, ул. Съездовская, д. 29, корп. 3 и 644033, г. Омск, ул. Старозагородная Роща, д. 8, 644033, г. Омск, ул. 1-я Затонская, д. 1/1, вне медицинской организации, а также в медицинских учреждениях, имеющих с Исполнителем соответствующие договоры.\n" +
                "10.\tПредоставление услуг по настоящему Договору происходит в порядке предварительной записи Потребителя на прием через единую регистратуру Исполнителя по телефону: (3812) 331-400 или по телефонам иных подразделений, указанных на сайте www.euromed-omsk.ru. В особых случаях, включая необходимость получения неотложной помощи, услуги предоставляются Потребителю без предварительной записи и/или вне установленной очереди.\n" +
                "11.\tПредоставление услуг по настоящему договору осуществляется в течение всего срока его действия.\n" +
                "12.\tПрава и обязанности Сторон установлены Правилами.\n" +
                "13.\tПотребитель вправе отказаться после заключения настоящего договора от получения медицинских услуг по собственной инициативе, предоставив соответствующий отказ от медицинского вмешательства. В случае отказа Потребителя от получения медицинских услуг, Потребитель оплачивает Исполнителю фактически понесенные Исполнителем расходы, связанные с исполнением обязательств по настоящему договору.\n" +
                "14.\tНастоящий договор прекращается до выполнения Исполнителем своих обязательств в следующих случаях:\n" +
                "a.\tпри отсутствии у Исполнителя объективной возможности оказать медицинскую услугу, в том числе в связи с:\n" +
                "- обнаружением Исполнителем (медицинским работником Исполнителя) противопоказаний у Потребителя для оказания медицинской услуги, которые на момент заключения Договора были Исполнителю неизвестны и стали таковыми в процессе обследования и лечения;\n" +
                "- ухудшением состояния здоровья Потребителя не позволяющим продолжать начатое лечение;\n" +
                "- отсутствием или непригодностью медицинского оборудования Исполнителя для оказания соответствующей медицинской услуги;\n" +
                "- неоплатой или несвоевременной оплатой медицинских услуг в соответствии с порядком оплаты, установленным настоящим договором.\n" +
                "15.\tНастоящий договор может быть изменен и расторгнут в любой момент времени по взаимному соглашению Сторон. \n" +
                "16.\tНастоящий договор признается заключенным с момента его подписания сторонами и прекращается по истечении 12 месяцев с даты его заключения, но не ранее полного исполнения Сторонами принятых на себя обязательств. Если за 10 календарных дней до истечения срока действия Договора ни одна из Сторон не заявит о его прекращении, Договор считается продленным на тот же срок и на тех же условиях.\n" +
                "17.\tСтороны несут ответственность за неисполнение или ненадлежащее исполнение условий настоящего договора в порядке, установленном действующим законодательством РФ и Правилами. \n" +
                "18.\tВсе споры, вытекающие из настоящего Договора, разрешаются сторонами путем переговоров. В случае невозможности урегулирования спора путем переговоров, спор подлежит разрешению в соответствии с действующим законодательством РФ.\n" +
                "19.\tДо заключения настоящего договора Потребитель уведомлен о том, что несоблюдение указаний и рекомендаций Исполнителя (медицинского работника, предоставляющего платную медицинскую услугу), в том числе назначенного режима лечения, могут снизить качество предоставляемой платной медицинской услуги, повлечь за собой невозможность ее завершения в срок или отрицательно сказаться на состоянии здоровья Потребителя.\n" +
                "20.\tВсе, что не предусмотрено настоящим Договором регулируется Правилами и действующим законодательством РФ.\n" +
                "21.\tУ каждой из Сторон находится один экземпляр настоящего договора. Все экземпляры имеют одинаковую юридическую силу. \n" +
                "22.\tПодписанием настоящего договора Потребитель разрешает \uF0A3  / не разрешает \uF0A3 Исполнителю использовать свою медицинскую документацию для ведения истории болезни Потребителя в электронном виде с использованием информационной базы Исполнителя.\n" +
                "С действующим Прейскурантом на медицинские услуги " + euromedShort + " Потребитель ознакомился.\n" +
                "Копию Правил Потребитель (Представитель Потребителя) получил.\n" +
                "\n" +
                "Исполнитель\n" +
                euromedShort + "\n" +
                "Адрес: 644024, г. Омск, ул. Съездовская, д. 29 корп. 3\n" +
                "ИНН 5504248024\n" +
                "ОГРН 1145543033943\n" +
                "(Свидетельство о государственной регистрации юридического лица \n" +
                "серия 55 номер 003798131, выдано Межрайонной инспекцией Федеральной налоговой службы № 12 по Омской области 20.08.2014 г.)\n" +
                "Лицензия на оказание медицинской деятельности " + contract.getLicense() + "\tПотребитель (представитель Потребителя)\n" +
                "\n" +
                "ФИО: " + contract.getFio4() + "\n" +
                "Адрес: " + contract.getAddress3() + "\n" +
                "Телефон: " + contract.getCELLULAR() + "\n" +
                "\n" +
                "\n" +
                "\n" +
                "Медицинский регистратор\n" +
                " " + contract.getREGISTRATOR2() + "\n" +
                "____________________________________\n" +
                "(ФИО, должность, подпись доверенного лица Исполнителя)\t\n" +
                "\n" +
                "_________________________\n" +
                "(подпись Потребителя (представителя Потребителя))\n";

        return responseBody;
    }

    private CustomUser getCustomerUser() {
        Object principal = null;
        try {
            principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (BadCredentialsException ex) {

        }
        CustomUser user = null;
        if (principal != null && principal instanceof CustomUser) {
            user = ((CustomUser) principal);
        }
        return user;
    }


}
