package org.example.mbean;

public interface HitsMBean {

    void registerShot(boolean isHit);

    int getHits();
    int getAll();

}
