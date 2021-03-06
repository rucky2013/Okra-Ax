package org.okraAx.internal;

import org.okraAx.internal.bean.AxCoInfo;
import org.okraAx.internal.config.AxProperties;
import org.okraAx.internal.bean.AxConfig;
import org.okraAx.internal.inner.axrpc.IrServer;
import org.okraAx.internal.zookeeper.AxZookeeper;

/**
 * @author TinyZ
 * @since 1.1
 */
public class InnerFramework {

    private AxZookeeper zookeeper;
    private IrServer irServer;
    private AxConfig config;
    private AxCoInfo info;

    public void start() {
        try {
            //  zookeeper service
            zookeeper = new AxZookeeper(AxProperties.axZkConnectString, AxProperties.axZkTimeout, AxProperties.axZkWatches, AxProperties.axZkRootPath, AxProperties.axModule, AxProperties.axId, info);
            zookeeper.init();
            //  inner service
            String id = String.valueOf(AxProperties.axId);
            inner = new IrServer(id, AxProperties.axPort);
            inner.start();
        } catch (Exception e) {
            zookeeper.close();
        }
    }

    public void stop() {
        if (irServer != null)
            irServer.stop();
    }

}
