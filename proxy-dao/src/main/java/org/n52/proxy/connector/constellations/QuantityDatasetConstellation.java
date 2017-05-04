package org.n52.proxy.connector.constellations;

import java.util.Date;
import static org.n52.proxy.connector.utils.EntityBuilder.createUnit;
import static org.n52.proxy.connector.utils.EntityBuilder.updateDatasetEntity;
import org.n52.proxy.db.beans.ProxyServiceEntity;
import org.n52.series.db.beans.CategoryEntity;
import org.n52.series.db.beans.DatasetEntity;
import org.n52.series.db.beans.FeatureEntity;
import org.n52.series.db.beans.OfferingEntity;
import org.n52.series.db.beans.PhenomenonEntity;
import org.n52.series.db.beans.ProcedureEntity;
import org.n52.series.db.beans.QuantityDatasetEntity;
import org.n52.series.db.beans.UnitEntity;

/**
 * @author Jan Schulte
 */
public class QuantityDatasetConstellation extends DatasetConstellation {

    private UnitEntity unit;

    public QuantityDatasetConstellation(String procedure, String offering, String category, String phenomenon,
            String feature) {
        super(procedure, offering, category, phenomenon, feature);
    }

    public UnitEntity getUnit() {
        return unit;
    }

    public void setUnit(UnitEntity unit) {
        this.unit = unit;
    }

    @Override
    public DatasetEntity createDatasetEntity(ProcedureEntity procedure, CategoryEntity category, FeatureEntity feature,
            OfferingEntity offering, PhenomenonEntity phenomenon, ProxyServiceEntity service) {
        QuantityDatasetEntity quantityDataset = new QuantityDatasetEntity();
        quantityDataset.setDomainId(this.getDomainId());
        updateDatasetEntity(quantityDataset, procedure, category, feature, offering, phenomenon, service);
        // add empty unit entity, will be replaced later in the repositories
        if (unit == null) {
            // create empty unit
            unit = createUnit("", null, service);
        }
        quantityDataset.setUnit(unit);
        quantityDataset.setFirstValueAt(new Date());
        quantityDataset.setLastValueAt(new Date());
        return quantityDataset;
    }

}
