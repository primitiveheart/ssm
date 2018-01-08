package com.zgb.vo;

/**
 * Created by admin on 2018/1/8.
 */
public class LibraryVo {
    private String id;
    private String targetId;
    private String pId;
    private String tatgetPId;
    /* "innee :成为子节点，prev:成为同级前一个节点，next: 成为同级后一个节点"*/
    private String moveType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getTatgetPId() {
        return tatgetPId;
    }

    public void setTatgetPId(String tatgetPId) {
        this.tatgetPId = tatgetPId;
    }

    public String getMoveType() {
        return moveType;
    }

    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }
}
