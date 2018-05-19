package com.xplosion;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSMMOApi;
import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.extensions.SFSExtension;

public class BrawlExtension extends SFSExtension {

    private ISFSMMOApi mmoApi;
    private UserVariablesHandler userVariablesHandler;

    @Override
    public void init() {
        ISFSMMOApi mmoAPi = SmartFoxServer.getInstance().getAPIManager().getMMOApi();
        UserVariablesHandler userVariablesHandler = new UserVariablesHandler(mmoAPi, this);

        addEventHandler(SFSEventType.USER_VARIABLES_UPDATE, userVariablesHandler);

        trace("###########-My first extension!-###########");
    }
}
