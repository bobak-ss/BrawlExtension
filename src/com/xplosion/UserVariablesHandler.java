package com.xplosion;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSMMOApi;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.smartfoxserver.v2.extensions.SFSExtension;
import com.smartfoxserver.v2.mmo.MMORoom;
import com.smartfoxserver.v2.mmo.Vec3D;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserVariablesHandler extends BaseServerEventHandler {

    ISFSMMOApi mmoApi;
    BrawlExtension brawlExtension;

    public UserVariablesHandler(ISFSMMOApi mmpApi, BrawlExtension extension)
    {
        this.mmoApi = mmpApi;
        this.brawlExtension = extension;
    }

    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException {
        List<UserVariable> variables = (List<UserVariable>) event.getParameter(SFSEventParam.VARIABLES);
        User user = (User) event.getParameter(SFSEventParam.USER);

        Map<String, UserVariable> varMap = new HashMap<String, UserVariable>();
        for (UserVariable var : variables)
            varMap.put(var.getName(), var);

        if(varMap.containsKey("px") && varMap.containsKey("py"))
        {
            if(Math.round(varMap.get("px").getDoubleValue()) % 10 == 0)
            {
                Vec3D pos = new Vec3D(
                        varMap.get("px").getDoubleValue().floatValue(),
                        varMap.get("py").getDoubleValue().floatValue(),
                        0
                );

                trace("user[" + user.getName() + "]: updated position(" + pos.floatX() + ", " + pos.floatY() + ")");
                mmoApi.setUserPosition(user, pos, user.getLastJoinedRoom());
            }
        }
    }
}
