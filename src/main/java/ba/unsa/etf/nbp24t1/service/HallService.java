package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.HallEntity;

import java.util.List;

public interface HallService {

    List<HallEntity> getAll();

    HallEntity getHallByHallNumber(Integer hallNumber);
}
