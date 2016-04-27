package com.lj.kernel.module.chat;


import com.lj.kernel.module.Room;

import java.util.Set;

/**
 * @author : TinyZ.
 * @email : ogcs_tinyz@outlook.com
 * @date : 2016/4/21
 */
public class ChatRoom implements Room {

    private long id;
    private Set<Long> users;


    public ChatRoom(long id) {
        this.id = id;
    }

    @Override
    public long id() {
        return id;
    }

    @Override
    public int type() {
        return 0;
    }

    @Override
    public void enter(String gateId, long uid){
    }

    @Override
    public void init() {

    }

    @Override
    public Set<Long> players() {
        return null;
    }

    @Override
    public void exit(Long user) {

    }

    @Override
    public void destroy() {

    }
}