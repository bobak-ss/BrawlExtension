package com.xplosion;

import com.smartfoxserver.v2.extensions.SFSExtension;

public class BrawlExtension extends SFSExtension {
    @Override
    public void init() {
        trace("my first extension!");
    }
}
