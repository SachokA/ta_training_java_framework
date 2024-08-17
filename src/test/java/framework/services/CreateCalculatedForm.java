package framework.services;


import framework.models.CalculatedForm;

import java.util.Map;

public class CreateCalculatedForm {

    public static CalculatedForm completeCalculatedForm(Map<String, String> formMap) {
        CalculatedForm form = CalculatedForm.create()
                .withProductName(formMap.get("Product Name"))
                .withNumberOfInstances(formMap.get("Number of Instances"))
                .withOperationSystem(formMap.get("Operating System / Software"))
                .withVMClass(formMap.get("Provisioning Model"))
                .withInstanceType(formMap.get("Machine type"))
                .withNumberOfGPUs(formMap.get("Number of GPUs"))
                .withGPUType(formMap.get("GPU Model"))
                .withLocalSSD(formMap.get("Local SSD"))
                .withDatacenterLocation(formMap.get("Region"))
                .build();
        return form;
    }
}