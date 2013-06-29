package net.canang.acropolis.core.dao;

import net.canang.acropolis.core.model.District;
import net.canang.acropolis.core.model.DistrictImpl;
import net.canang.acropolis.core.model.State;
import net.canang.acropolis.core.model.StateImpl;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AcropolisConfiguration.class})
public class StateDaoTest {

    private Logger log = LoggerFactory.getLogger(StateDaoTest.class);

    @Autowired
    private StateDao stateDao;

    @Autowired
    private DistrictDao districtDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void createState() {
        State state = new StateImpl();
        state.setCode("JDT");
        state.setDescription("JOHOR DARUL TAKZIM");
        stateDao.save(state);

        State found = stateDao.findByCode("JDT");
        List<String> districts = new ArrayList<String>();
        districts.add("SEGAMAT");
        districts.add("SEKIJANG");
        districts.add("LEDANG");
        districts.add("PAGOH");
        districts.add("BAKRI");
        districts.add("MUAR");
        districts.add("PARIT SULONG");
        districts.add("LABIS");
        districts.add("SRI GADING");
        districts.add("AYER HITAM");
        districts.add("KLUANG");
        districts.add("SEMBRONG");
        districts.add("MERSING");
        districts.add("BATU PAHAT");
        districts.add("SIMPANG RENGGAM");
        districts.add("TENGGARA");
        districts.add("KOTA TINGGI");
        districts.add("PONTIAN");
        districts.add("KULAI");
        districts.add("TEBRAU");
        districts.add("TANJONG PIAI");
        districts.add("GELANG PATAH");
        districts.add("PULAI");
        districts.add("JOHOR BAHRU");
        districts.add("PASIR GUDANG");
        districts.add("PENGERANG");

        DecimalFormat format = new DecimalFormat("00");
        for (int i = 0, districtsSize = districts.size(); i < districtsSize; i++) {
            String d = districts.get(i);
            District district = new DistrictImpl();
            district.setCode(format.format(i));
            district.setDescription(d);
            district.setState(found);
            districtDao.save(district);
        }
    }
}
