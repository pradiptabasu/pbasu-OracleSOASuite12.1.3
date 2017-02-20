package readjsessionid;

import com.collaxa.cube.engine.types.bpel.CXPartnerLink;
import com.collaxa.cube.util.logging.LogFactory;
import com.collaxa.cube.ws.HeaderHandler;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.logging.Logger;

public class InboundHandlerForFetchingSessionID implements HeaderHandler {
    public InboundHandlerForFetchingSessionID() {
        super();
    }

    @Override
    public void invoke(CXPartnerLink cXPartnerLink, String pOperationName, Map pMsgPayload, List pListHeader,
                       Map pAgnosticCallProps) throws Exception {
        // TODO Implement this method

        //public final Log logger = LogFactory.getLogger2("");
                      
        System.out.println("Printing for pMsgPayload ");
        for (Object key : pMsgPayload.keySet()) {
            System.out.println("\t\tKey : " + key.toString() + " Value : " + pMsgPayload.get(key));
        }
        
        System.out.println("Printing for pMsgPayload ");
        for (int i = 0; i < pListHeader.size(); i++) {
                System.out.println(""+pListHeader.get(i).getClass());
        }
        
        System.out.println("Printing for pAgnosticCallProps ");
        for (Object key : pAgnosticCallProps.keySet()) {
            System.out.println("\t\tKey : " + key.toString() + " Value : " + pAgnosticCallProps.get(key));
        }

        Map httpHeaders = (Map) pAgnosticCallProps.get("HTTP_RESPONSE_HEADERS");
        System.out.println("Printing for httpHeaders ");
        for (Object key : httpHeaders.keySet()) {
            System.out.println("\t\tKey : " + key.toString() + " Value : " + httpHeaders.get(key));
        }
        
        String cookieValue = (String) httpHeaders.get("HTTP_SET_COOKIE");
        if (cookieValue != null && cookieValue.length() > 0 && cookieValue.indexOf(";") > 0) {
            cookieValue = cookieValue.substring(0, cookieValue.indexOf("; path"));
            System.out.println("Cookie to forward: " + cookieValue);
            cXPartnerLink.setPropertyString("HTTP_SET_COOKIE", cookieValue);
            //cXPartnerLink.setProperty("HTTP_SET_COOKIE", cookieValue);
        } else {
            // We must be on a subsequent connection, not the orignal connection attempt.
            System.out.println("This is an ongoing call, no set-cookie returned!");
        }
    }
}
