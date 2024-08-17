package framework.services;


import framework.models.GoogleCloudMainForm;
import framework.utils.PropertiesUtils;


public class GoogleCloudMainFormCompleting {

    public static GoogleCloudMainForm completeForm() {
        GoogleCloudMainForm completeGoogleCloudMainForm = GoogleCloudMainForm.create()
                .withNumberOfInstances(PropertiesUtils.get().readNumberOfInstances())
                .withOperationSystem(PropertiesUtils.get().readOperatingSystem())
                .withVMClass(PropertiesUtils.get().readVmClass())
                .withInstanceSeries(PropertiesUtils.get().readInstanceSeries())
                .withInstanceType(PropertiesUtils.get().readInstanceType())
                .withNumberOfGPUs(PropertiesUtils.get().readNumberOfGpus())
                .withGPUType(PropertiesUtils.get().readGpuType())
                .withLocalSSD(PropertiesUtils.get().readLocalSsd())
                .withDatacenterLocation(PropertiesUtils.get().readDatacenterLocation())
                .build();
        return completeGoogleCloudMainForm;
    }
}