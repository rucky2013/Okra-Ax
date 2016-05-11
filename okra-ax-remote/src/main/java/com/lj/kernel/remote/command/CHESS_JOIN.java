package com.lj.kernel.remote.command;

import com.lj.kernel.gpb.generated.GpbChess.ReqChessJoin;
import com.lj.kernel.gpb.generated.GpbChess.ResChessJoin;
import com.lj.kernel.module.chess.Chessboard;
import com.lj.kernel.remote.RemoteCommand;
import org.ogcs.app.Session;
import org.ogcs.ax.component.AxConnector;
import org.ogcs.ax.component.inner.AxReplys;
import org.ogcs.ax.gpb.OkraAx.AxInbound;

/**
 * @author : TinyZ.
 * @email : ogcs_tinyz@outlook.com
 * @date : 2016/4/14
 */
public class CHESS_JOIN extends RemoteCommand {

    @Override
    public void execute(Session session, AxInbound inbound) throws Exception {
        AxConnector axConnector = (AxConnector) session.getConnector();

        ReqChessJoin reqChessJoin = ReqChessJoin.parseFrom(inbound.getData());

        Chessboard room = (Chessboard) roomManager.getByUid(inbound.getSource());
        if (room == null) {
            room = new Chessboard(reqChessJoin.getRoomId());
            roomManager.put(inbound.getSource(), room);
        }
        room.enter(axConnector.id(), inbound.getSource());

        session.writeAndFlush(
                AxReplys.axOutbound(inbound.getRid(),
                        ResChessJoin.newBuilder()
                                .setSide(room.index(inbound.getSource()))
                                .build(),
                        inbound.getSource()
                )
        );
        if (room.players().size() >= 2) {
            room.init();
        }
    }
}
