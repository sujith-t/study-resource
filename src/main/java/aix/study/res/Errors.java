
package aix.study.res;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author Sujith T
 * 
 * <!In God We Trust>
 */
public class Errors {
    
    private static Map<Code, String> errorMap = null;
    
    public enum Code {
        RES_NO_ERROR,
        RES_SYS_ERROR,
        RES_UTR_REQ_ID,
        RES_UTR_DUPLICATE_ID,
        RES_UTR_REQ_NAME,
        RES_UTR_REQ_AUTHOR,
        RES_UTR_REQ_RESTYPE,
        RES_UTR_MISMATCH_RESTYPE,
        RES_UTR_REQ_URL,
        RES_UTR_INVALID_URL,
        RES_UTR_DUPLICATE_URL,
        RES_UTR_NO_YTRESOURCE_UPDATE
    }
    
    private static void loadErrorMap() {
        errorMap = new EnumMap<>(Errors.Code.class);
        errorMap.put(Code.RES_NO_ERROR, "No errors");
        errorMap.put(Code.RES_SYS_ERROR, "RES Exception Error: ");
        errorMap.put(Code.RES_UTR_REQ_ID, "Youtube resource id required, can't be empty");
        errorMap.put(Code.RES_UTR_DUPLICATE_ID, "Can't create a YouTube resource with an existing ID");
        errorMap.put(Code.RES_UTR_REQ_NAME, "Youtube resource name required, can't be empty");
        errorMap.put(Code.RES_UTR_REQ_AUTHOR, "Youtube resource creator required, can't be empty");
        errorMap.put(Code.RES_UTR_REQ_RESTYPE, "Youtube resource type required, can't be empty");
        errorMap.put(Code.RES_UTR_MISMATCH_RESTYPE, "Youtube resource type mismatch, invalid type");
        errorMap.put(Code.RES_UTR_REQ_URL, "Youtube resource URL required, can't be empty");
        errorMap.put(Code.RES_UTR_INVALID_URL, "Youtube resource URL is invalid");
        errorMap.put(Code.RES_UTR_DUPLICATE_URL, "Youtube resource URL is already available");
        errorMap.put(Code.RES_UTR_NO_YTRESOURCE_UPDATE, "No such Toutube resource available to update");
    }
    
    public static String getInfo(Errors.Code code) {
        if(errorMap == null) {
            loadErrorMap();
        }
        
        return errorMap.get(code);
    }
}
