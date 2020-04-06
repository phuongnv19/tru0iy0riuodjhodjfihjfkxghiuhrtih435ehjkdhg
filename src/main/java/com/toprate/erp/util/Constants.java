package com.toprate.erp.util;

public class Constants {

    public static final String TEMPLATE_PRODUCT_IMPORT = "Template_Import_SaleInventory.xlsx";
    public static final String TEMPLATE_PRODUCT_IMPORT_FAILS = "Template_Import_SaleInventory_Fails.xlsx";
    public static final String TEMPLATE_STOCK_IMPORT = "Template_Import_StockInventory.xlsx";
    public static final String TEMPLATE_STOCK_IMPORT_FAILS = "Template_Import_StockInventory_Fails.xlsx";
    public static final String FOLDER_SAVE_TEMPLATE_ERR = "result_file_error";
    public static final String FOLDER_SAVE_FILE_IMPORT_PRODUCT = "save_file_product";

    public interface TABLE_NAME {
        public static final String ACTIVITY = "activity";
    }

    public interface PERSION_TYPE {
        public static final Long USER = 1l;
        public static final Long MEMBER = 2l;
    }

    public interface REACT_TYPE {
        public static final Long JOIN = 1l;
        public static final Long NOTJOIN = 2l;
    }

    public interface ACTIVITY_TYPE {
        public static final Long POST = 1l;
        public static final Long EVENT = 2l;
    }

}
