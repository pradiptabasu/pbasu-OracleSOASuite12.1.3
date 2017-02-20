package readjsessionid;

import com.collaxa.cube.engine.types.bpel.CXPartnerLink;
import com.collaxa.cube.ws.HeaderHandler;

import java.util.List;
import java.util.Map;

public class OutboundHandlerForSendingSessionID implements HeaderHandler {
    public OutboundHandlerForSendingSessionID() {
        super();
    }

    @Override
    public void invoke(CXPartnerLink cXPartnerLink, String string, Map map, List list, Map map2) throws Exception {
        // TODO Implement this method

    }
}
