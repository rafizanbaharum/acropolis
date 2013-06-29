package net.canang.acropolis.core.dao;

import net.canang.acropolis.core.model.District;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
public interface DistrictDao {

    District findById(Long id);

    List<District> find();

    void save(District district);

    void update(District district);

    void remove(District district);
}
