package com.boz.commons.test;

public class Constants {

    //Misc
    public static final String FORMATDATE = "dd.MM.yyyy";
    public static final String FORMATDATTIME = "dd.MM.yyyy HH24:MI:SS";
    public static final String OLDFORMATSTRING = "'";
    public static final String NEWFORMATSTRING = "''";
    public static final String FORMATDATED = "yyyy-MM-dd";

    //MPS User for debug
    public static final String DEBUG_DEFAULT_USER_FOR_SERVICE = "U901796";

    //User to be sent to COR if domain unknown
    public static final String EXTERN_USER_COR = "POSK0";

    //Parameter's name
    //Functional parameter
    public static final String AFP_MIN_RISKCLASS = "AFP_MIN_RISKCLASS";
    public static final String AFP_MAX_RISKCLASS = "AFP_MAX_RISKCLASS";
    //public static final String AFP_BMI_PRF_MIN = "AFP_BMI_PRF_MIN" //TODO JSF Remove ? while no more use 
    //Public Shared ReadOnly AFP_BMI_PRF_MAX = "AFP_BMI_PRF_MAX" //TODO JSF Remove ? while no more use 
    //public static final String AFP_MIN_PRF_AN = "AFP_MIN_PRF_AN" //TODO JSF Remove ? while no more use 
    //public static final String VARIANTE_PREFERRED = "AFP_TV_PREFERRED" //TODO JSF Remove ? while no more use 
    //public static final String VARIANTE_BASIC = "AFP_TV_BASIC" //TODO JSF Remove ? while no more use 
    //public static final String VARIANTE_RAUCHER = "AFP_TV_RAUCHER" //TODO JSF Remove ? while no more use 
    public static final String TAX = "AFP_TAXRATE";
    //Technical parameter
    //public static final String ATP_QUESTION_SIZE_WEIGHT; = "ATP_QUESTION_SIZE_WEIGHT"
    //public static final String ATP_QUESTION_SMOKING = "ATP_QUESTION_SMOKING"
    //public static final String ATP_QUESTION_NON_SMOKING = "ATP_QUESTION_NON_SMOKING"
    //public static final String ATP_QUESTION_AIR_SPORTS = "ATP_QUESTION_AIR_SPORTS"
    //public static final String ATP_QUESTION_DIVING_SPORTS = "ATP_QUESTION_DIVING_SPORTS"
    public static final String AFP_PRF_SURVEYTYPEID = "AFP_PRF_SURVEYTYPEID";
    public static final String ATP_PRF_IND_SURVEYTYPEID = "ATP_PRF_IND_SURVEYTYPEID";
    public static final String CATEGORY_INCAPACITY = "ATP_INV_CATEGORY";
    public static final String CATEGORY_CAPITALIZATION = "ATP_CAP_CATEGORY";
    public static final String CATEGORY_LIBERATION = "ATP_LIB_CATEGORY";
    public static final String CATEGORY_FUND = "ATP_FUND_CATEGORY";
    public static final String CATEGORY_ANNUITY = "ATP_RV_CATEGORY";
    public static final String CATEGORY_DEATH = "ATP_DEC_CATEGORY";

    public static final int LNG_GERMAN = 1;
    public static final int LNG_FRENCH = 2;
    public static final int LNG_ITALIAN = 3;

    public static final String SURPLUSESPLAN_0 = "AFP_SURPLUSES_PLAN_0";
    public static final String SURPLUSESPLAN_1 = "AFP_SURPLUSES_PLAN_1";
    public static final String SURPLUSESPLAN_2 = "AFP_SURPLUSES_PLAN_2";
    public static final String SURPLUSESPLAN_4 = "AFP_SURPLUSES_PLAN_4";


    //*** Product
    //* General Level
    public static final String P_BEGINNINGDATE = "PG_BD"; //JSF OK
    public static final String P_HEADNUMBER = "PG_HN"; //JSF OK
    public static final String P_PAYMENTMODE = "PG_PM"; //JSF OK
    public static final String P_EMPLOYEEDISCOUNT = "PG_ED"; //JSF OK
    public static final String P_EMPLOYEE_UNR = "PG_UNR"; //JSF OK
    public static final String P_COMMISSIONRENUNCIATION = "PG_CR"; //JSF OK
    public static final String P_M = "PG_IM"; //JSF OK
    public static final String P_PRECAUTIONTYPE = "PG_PT"; //JSF OK
    public static final String P_MEANSPAYMENT = "PG_PA"; //JSF OK
    public static final String P_MEDICALVISITA = "PG_MVA"; //JSF OK
    public static final String P_MEDICALVISITB = "PG_MVB"; //JSF OK
    public static final String P_PROPOSALQUESTIONA = "PG_PQA"; //JSF OK
    public static final String P_PROPOSALQUESTIONB = "PG_PQB"; //JSF OK
    public static final String P_POLICY_HOLDER_COUNTRY = "PG_PHC"; //JSF OK
    public static final String P_GROSSPREMIUM = "PG_GP"; //JSF OK
    public static final String P_MAXIMUMOPP3 = "PG_MO"; //JSF OK
    public static final String P_PRIMACYCHOISE = "PG_PC"; //JSF OK

    public static final String P_ENSURED_A_ENTRY_AGE_IN_YEAR = "PG_AEAY"; //JSF OK
    public static final String P_ENSURED_B_ENTRY_AGE_IN_YEAR = "PG_BEAY"; //JSF OK
    public static final String P_ENSURED_A_ENTRY_AGE_IN_DAY = "PG_AEAD"; //JSF OK
    public static final String P_ENSURED_B_ENTRY_AGE_IN_DAY = "PG_BEAD"; //JSF OK
    public static final String P_AGECALCMODE = "PG_ACM";
    public static final String P_FIXEDCOSTS = "PG_FC"; //JSF OK
    public static final String P_TAX = "PG_VT"; //JSF OK
    public static final String P_WITHTAX = "PG_WT"; //JSF OK
    public static final String P_ENDDATE = "PG_ENDD"; //JSF OK
    public static final String P_CURRENCY = "PG_CU"; //JSF OK
    public static final String P_OFFER_CALCULATED_ONCE = "PG_OCO";
    public static final String P_GUARANTEED_DEATH_CAPITAL_MESSAGE = "PG_GDCM";
    //* Projection Level
    public static final String P_DEATHCAPITAL_PROJECTION = "PP_IDC"; //JSF OK
    public static final String P_GROSSPREMIUM_PROJECTION = "PP_GP"; //JSF OK
    public static final String P_SURPLUSVALUE_PROJECTION = "PP_SPV2"; //JSF OK
    public static final String P_NETPREMIUM_PROJECTION = "PP_NP"; //JSF OK
    public static final String P_FIXEDCOSTS_PROJECTION = "PP_FC"; //JSF OK
    public static final String P_SPLITTED_GROSS_PREMIUM_PROJECTION = "PP_SGP"; //JSF OK
    public static final String P_SPLITTED_SURPLUSES_2_VALUE_PROJECTION = "PP_SSPV2"; //JSF OK
    public static final String P_SPLITTED_NET_PREMIUM_PROJECTION = "PP_SNP"; //JSF OK
    public static final String P_ANNUALLIFEANNUITYPROJECTION = "PP_ALA"; //JSF OK
    public static final String P_PRESUMEDANNUALLIFEANNUITY_PROJECTION = "PP_PALA"; //JSF OK
    //* Simulatino Level
    public static final String P_DEATHCAPITAL_SIMULATION = "PS_IDC"; //JSF OK -> VUS



    //*** Tariff
    //* General Level
    public static final String T_ENSURED_A_EXIT_AGE = "TG_AXA"; //?
    public static final String T_ENSURED_B_EXIT_AGE = "TG_BXA"; //?
    public static final String TG_AEA = "TG_AEA"; //?
    public static final String T_N = "TG_IN"; //JSF OK 
    public static final String T_M = "TG_IM"; //JSF OK 
    public static final String T_HEADENSURED = "TG_HE"; //JSF OK 
    public static final String T_BEGINNINGDATE = "TG_BD"; //JSF OK 
    public static final String T_GUARANTEEDATE = "TG_GD"; //JSF OK 
    public static final String T_EXTERNORDER = "TG_TO";
    public static final String T_ACCIDENT = "TG_AC"; //JSF OK 
    public static final String T_WARRANTY = "TG_TW"; //JSF OK 
    public static final String T_SURPLUSPLAN = "TG_SP"; //JSF OK 
    public static final String T_DEATHCAPITAL = "TG_IDC"; //JSF OK 
    public static final String T_GUARANTEECAPITAL = "TG_GC"; //JSF OK 
    public static final String T_ANNUALANNUITY = "TG_AA"; //JSF OK 
    public static final String T_ANNUALLIFEANNUITY = "TG_ALA"; //JSF OK 
    public static final String T_WAITINGPERIOD = "TG_WP"; //JSF OK 
    public static final String T_LIBERATIONDELAY = "TG_LD"; //JSF OK 
    public static final String T_WITHDEATHCAPITAL = "TG_WDC"; //JSF OK 
    public static final String T_ANNUITY_PAYMENT_MODE = "TG_APM"; //JSF OK  -> VUS
    public static final String T_LIFE_ANNUITY_PAYMENT_MODE = "TG_LAPM"; //JSF OK  
    public static final String T_PROPERTY_FUND = "TG_FU"; //JSF OK Used for tariff IF -> InitializeTariffsSeizureRules
    public static final String T_CLASSRISK = "TG_RC"; //JSF OK Used for tariff ER
    public static final String T_TARIFFVARIANT = "TG_TV"; //JSF OKUsed for tariff Preferred
    public static final String T_GROSSPREMIUM = "TG_GP"; //JSF OK -> VUS
    public static final String T_ENDDATE = "TG_ED"; //JSF OK -> SetValPreDef
    public static final String T_NEEDANACTIVITY = "TG_NA"; //JSF OK -> NeedAnActivity
    public static final String T_NEEDATRAININGLEVEL = "TG_NTL"; //JSF OK -> NeedATrainingLevel
    public static final String T_ENDPAYMENTDATE = "TG_EM"; // JSF OK
    public static final String T_CORRISKCLASS = "TG_CRC";
    public static final String T_COSTVARIANT = "TG_CCV"; //JSF Ok
    //public static final String T_TYPERISK = "TG_TR"
    public static final String T_RATEOFINTEREST_UNFAVORABLE = "TG_ROI1";
    public static final String T_RATEOFINTEREST_CURRENT = "TG_ROI2";
    public static final String T_RATEOFINTEREST_FAVORABLE = "TG_ROI3";
    public static final String T_CALCULATION_MODE = "TG_CT";
    public static final String T_TECHNICAL_RATE = "TG_STR";
    public static final String T_DEATHFINALFACTOR = "TG_RCD";
    public static final String T_BMICATEGORY = "TG_BMIC";
    public static final String T_GUARANTEEDEATHCAPOPT = "TG_GDCO";
    public static final String T_GUARANTEEDEATHCAPVALUE = "TG_GDCV";
    public static final String T_GUARANTEEDEATHCAPPERCENTAGE = "TG_GDCP";

    //* Projection Level
    public static final String T_GROSSPREMIUM_PROJECTION = "TP_GP"; //JSF OK
    public static final String T_DEATHPREMIUM_PROJECTION = "TP_DP"; //JSF OK
    public static final String T_LIFEPREMIUM_PROJECTION = "TP_LP"; //JSF OK
    public static final String T_SURPLUSVALUE2_PROJECTION = "TP_SPV2"; //JSF OK
    public static final String T_SURPLUSVALUE1_PROJECTION = "TP_SPV1"; //JSF OK
    public static final String T_SURPLUSVALUE4_PROJECTION = "TP_SPV4"; //JSF OK
    public static final String T_NETPREMIUM_PROJECTION = "TP_NP"; //JSF OK
    public static final String T_INVESTEDAMOUNT_PROJECTION = "TP_IA"; //JSF OK
    public static final String T_INCAPACITYBENEFIT_PROJECTION = "TP_AA"; //JSF OK
    public static final String T_DEATHBENEFIT_PROJECTION = "TP_IDC"; //JSF OK
    public static final String T_ANNUALLIFEANNUITY_PROJECTION = "TP_ALA"; //JSF OK
    public static final String T_PRESUMEDANUALLIFEANNUITY_PROJECTION = "TP_PALA"; //JSF OK
    public static final String T_LIBERATIONPREMIUM_PROJECTION = "TP_RP"; //JSF OK
    public static final String T_ACQUISITIONCOSTS_PROJECTION = "TP_TCC"; //JSF OK
    public static final String T_EMISSIONCOSTS_PROJECTION = "TP_IC"; //JSF OK
    public static final String T_ACTUALVALUE_PROJECTION = "TP_AV"; //JSF OK
    public static final String T_PRESUMEDLIFEBENEFIT_PROJECTION = "TP_PILC"; //JSF OK
    public static final String T_LIFEBENEFIT_PROJECTION = "TP_ILC"; //JSF OK
    public static final String T_EXEMPTEDPREMIUM_PROJECTION = "TP_EP"; //JSF OK
    public static final String T_GROSSFUNDVALUE_PROJECTION = "TP_GFV"; //JSF OK
    public static final String T_SURRENDERVALUE_PROJECTION = "TP_SV"; //JSF OK
    public static final String T_TOTALSURRENDERVALUE_PROJECTION = "TP_TSV"; //JSF OK
    public static final String T_CONVERSIONVALUE_PROJECTION = "TP_CV"; //JSF OK
    public static final String T_PRESUMEDDEATHBENEFIT_PROJECTION = "TP_PIDC"; //JSF OK
    public static final String T_ANNUAL_DECEASE_ANNUITY_PROJECTION = "TP_ADA"; //JSF OK
    public static final String T_NETRISKPREMIUM_PROJECTION = "TP_PR"; //JSF OK
    public static final String T_NETSAVEPREMIUM_PROJECTION = "TP_PS"; //JSF OK
    public static final String T_DECLINEINSUREDSUM_PROJECTION = "TP_DIS"; //JSF OK
    public static final String T_BONUSSURRENDERVALUE_PROJECTION = "TP_BSV"; //JSF OK
    public static final String T_YIELDRATE_PROJECTION = "TP_YR"; //JSF OK
    public static final String T_SPLITTEDGROSSPREMIUM_PROJECTION = "TP_SGP"; //JSF OK
    public static final String T_ANNUALIZEDSPLITTEDGROSSPREMIUM_PROJECTION = "TP_ASGP"; //JSF OK
    public static final String T_SURPLUSES_RATE_CURRENT = "TP_SR2";
    public static final String T_SURPLUSES_RATE_FAVORABLE = "TP_SR3";
    public static final String T_SURPLUSES_RATE_UNFAVORABLE = "TP_SR1";




    //* Simulation Level
    //public static final String T_GROSSLIFECAPITAL_SIMULATION = "TS_GC"
    //public static final String T_GROSSLIFEMIXCAPITAL_SIMULATION = "TS_ILC"
    public static final String T_NETRISKPREMIUM_SIMULATION = "TS_PR"; //JSF OK
    public static final String T_MANAGECOSTS_SIMULATION = "TS_TMC"; //JSF OK
    public static final String T_NETFUNDVALUE_SIMULATION = "TS_NFV"; //JSF OK
    public static final String T_PRESUMEDLIFEBENEFIT_SIMULATION = "TS_PILC"; //JSF OK
    public static final String T_GROSSFUNDVALUE_SIMULATION = "TS_GFV"; //JSF OK
    public static final String T_SURPLUSVALUE1_SIMULATION = "TS_SPV1"; //JSF OK
    public static final String T_SURPLUSVALUE2_SIMULATION = "TS_SPV2"; //JSF OK
    public static final String T_SURPLUSVALUE4_SIMULATION = "TS_SPV4"; //JSF OK
    public static final String T_SURRENDERVALUE_SIMULATION = "TS_SV"; //JSF OK
    public static final String T_PRESUMEDDEATHBENEFIT_SIMULATION = "TS_PIDC"; //JSF OK
    public static final String T_DEATHBENEFIT_SIMULATION = "TS_IDC"; //JSF OK ; //Needed by VUS also
    public static final String T_CONVERSIONVALUE_SIMULATION = "TS_CV"; //JSF OK
    public static final String T_YIELDRATE_SIMULATION = "TS_YR";

    //*** ValPreDef
    public static final String VPD_ENSURED_A_SEX = "VPD_ASEX";
    public static final String VPD_ENSURED_A_ENTRY_AGE_IN_YEAR = "VPD_AEAY"; //ensured A Entry Age in Year
    public static final String VPD_ENSURED_A_ENTRY_AGE_IN_YEAR_COMPLETED = "VPD_AEAY_C"; //ensured A Entry Age in completed Year
    public static final String VPD_ENSURED_A_ENTRY_AGE_IN_DAY = "VPD_AEAD"; //ensured A Entry Age in Day
    public static final String VPD_ENSURED_A_ACTIVITY = "VPD_AACT";
    //public static final String VPD_ENSURED_A_FOLLOWINGKEY = "VPD_AFWK"
    public static final String VPD_ENSURED_A_TRAININGLEVEL = "VPD_ATL";
    public static final String VPD_ENSURED_A_DATE_OF_BIRTH = "VPD_ADOB";
    public static final String VPD_ENSURED_A_CATEGORY = "VPD_ACAT";
    //public static final String VPD_ENSURED_A_SECTOR = "VPD_ASEC"
    public static final String VPD_ENSURED_B_SEX = "VPD_BSEX";
    public static final String VPD_ENSURED_B_ENTRY_AGE_IN_YEAR = "VPD_BEAY"; //ensured B Entry Age in Year
    public static final String VPD_ENSURED_B_ENTRY_AGE_IN_DAY = "VPD_BEAD"; //ensured B Entry Age in Day
    public static final String VPD_PAYMENTMODE = "VPD_PM";
    public static final String VPD_HEADNUMBER = "VPD_HN";
    public static final String VPD_ENSURED_B_ACTIVITY = "VPD_BACT";
    //public static final String VPD_ENSURED_B_FOLLOWINGKEY = "VPD_BFWK"
    public static final String VPD_ENSURED_B_TRAININGLEVEL = "VPD_BTL";
    public static final String VPD_ENSURED_B_DATE_OF_BIRTH = "VPD_BDOB";
    public static final String VPD_ENSURED_B_CATEGORY = "VPD_BCAT";
    //public static final String VPD_ENSURED_B_SECTOR = "VPD_BSEC"
    public static final String VPD_BEGINNINGDATE = "VPD_BD";
    public static final String VPD_ENSURED_A_STATUS = "VPD_ASTA";
    public static final String VPD_ENSURED_B_STATUS = "VPD_BSTA";
    public static final String VPD_DISTRIBUTIONCHANNEL = "VPD_DC"; //Distribution Channel
    public static final String VPD_MAX_N_ON_ENSURED_A = "VPD_MNA"; //Max N ensured A (only selected tariff)
    public static final String VPD_MAX_N_ON_ENSURED_B = "VPD_MNB"; //Max N ensured B (only selected tariff)
    public static final String VPD_MAX_M_ON_ENSURED_A = "VPD_MMA"; //Max N ensured A (only selected tariff)
    public static final String VPD_MAX_M_ON_ENSURED_B = "VPD_MMB"; //Max N ensured B (only selected tariff)
    public static final String VPD_MAX_PRODUCT_END_DATE = "VPD_PED"; //Max End date of the product (= max of end date of all it//s tariffs)
    public static final String VPD_CONTRACT_STATE = "VPD_STATE"; //State of the contract
    public static final String VPD_ESTABLISHMENTDATE = "VPD_ED"; //Establishment date
    public static final String VPD_LBA = "VPD_LBA"; // Contract need LBA 

    public static final String VPD_ENSURED_A_PNUMMER = "VPD_EA_PN"; //Ensured A PNummer
    public static final String VPD_ENSURED_B_PNUMMER = "VPD_EB_PN"; //Ensured B PNummer
    public static final String VPD_CONTRACTOR_PNUMMER = "VPD_CONT_PN"; //Contractor PNummer
    public static final String VPD_PAYER_PNUMMER = "VPD_PAY_PN"; //Payer PNummer
    public static final String VPD_ADDRESSCONTRACTOR_PNUMMER = "VPD_ADCONT_PN"; //Address Contractor PNummer
    public static final String VPD_ADDRESSPAYER_PNUMMER = "VPD_ADPAY_PN"; //Address Payer PNummer
    public static final String VPD_ENTITLED_PNUMMER = "VPD_ENT_PN"; //Entitled PNummer
    public static final String VPD_LEGALDOMICIL_PNUMMER = "VPD_LD_PN"; //Legal Domicil PNummer
    //*** KEYS
    public static final String ON_CLICK = "OCL";
    public static final String ON_FOCUS = "OFO";
    public static final String ON_BLUR = "OBL";
    public static final String ON_CHANGE = "OCH";
    public static final String LABEL = "LIB";
    public static final String SCREEN_PRESENTATION = "CDE";
    public static final String DEFAULT_VALUE = "DEF";
    public static final String MAX_VALUE = "MAX";
    public static final String MIN_VALUE = "MIN";
    public static final String MANDATORY_VALUE = "IMP";
    public static final String LIST_VALUES = "LST";
    public static final String ORDER_TO_CHECK = "ORD";
    public static final String ORDER_TO_DISPLAY = "DSP";
    public static final String OBJECT_TYPE = "OBJ";
    public static final String DOMAIN_VALUES = "DOM";
    public static final String XPATH_OUT_COR = "XPO";
    public static final String XPATH_IN_COR = "XPI";
    public static final String LEVEL = "LEV";
    public static final String DATA_TYPE = "DTY";
    public static final String PROPERTY_ID = "ID";
    public static final String INFO_PROPERTY = "INFO";
    public static final String WS_PROPERTY = "WSPROP";

    //const to configure the interval of time (in days) in which contracts can be searched through the POS interface
    public static final String ATP_SEARCHLIMIT = "ATP_SearchLimit";

    public static final String VERTICAL = "V";
    public static final String HORIZONTAL = "H";

    public static final int LEVEL_GENERAL = 1;
    public static final int LEVEL_PROJECTION = 2;
    public static final int LEVEL_SIMULATION = 3;

    //Consts used to call RP webservice
    public static final String RP_WEBSERVICE_URL_APPSETTINGS_TAG = "cga-ws";
    public static final String RP_WEBSERVICE_NAME_TAG = "ServiceRP.asmx";

    //Constant for ENTRIS, type of count
    public static final String ENTRIS_ACCOUNT_SAMMELKONTO = "s";
    public static final String ENTRIS_ACCOUNT_EINZELKONTO = "e";
    public static final String ENTRIS_ACCOUNT_UNKNOWN = "x";

    public static final String ENTRIS_EXTERNPARTNERID_BEGU_WRONG_TYPE = "1";
    public static final int ENTRIS_EXTERNPARTNERID_BEGU_MAX_LENGTH = 8;

	
}
