package com.lj.kernel.remote.command;

import com.lj.kernel.gpb.generated.GpbChess.ResChessRoomInfo;
import com.lj.kernel.gpb.generated.GpbChess.ResChessRoomInfo.RoomInfo;
import com.lj.kernel.module.Room;
import com.lj.kernel.remote.RemoteCommand;
import org.ogcs.app.Session;
import org.ogcs.ax.component.inner.AxReplys;
import org.ogcs.ax.gpb.OkraAx.AxInbound;

import java.util.Set;

/**
 * @author : TinyZ.
 * @email : ogcs_tinyz@outlook.com
 * @date : 2016/4/14
 */
public class CHESS_ROOM_LIST extends RemoteCommand {

    @Override
    public void execute(Session session, AxInbound request) throws Exception {
        Set<Room> allRoom = roomManager.getAllRoom();
        ResChessRoomInfo.Builder builder = ResChessRoomInfo.newBuilder();
        for (Room room : allRoom) {
            RoomInfo.Builder roomInfo = RoomInfo.newBuilder();
            roomInfo.setId((int) room.id());
            Set<Long> players = room.players();
            for (Long uid : players) {
                roomInfo.addPlayers("visual - " + uid.toString());
            }
            builder.addInfos(roomInfo);
        }
        session.writeAndFlush(
                AxReplys.axOutbound(request.getRid(),
                        builder.build(),
                        request.getSource()
                )
        );
    }
}
