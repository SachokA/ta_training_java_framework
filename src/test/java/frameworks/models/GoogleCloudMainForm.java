package frameworks.models;

import java.util.Objects;

public class GoogleCloudMainForm {
    private String productName;
    private String numberOfInstances;
    private String operationSystem;
    private String VMClass;
    private String instanceSeries;
    private String instanceType;
    private String numberOfGPUs;
    private String GPUType;
    private String localSSD;
    private String datacenterLocation;
    private String committedUsage;

    public static class Builder {
        private String productName;
        private String numberOfInstances;
        private String operationSystem;
        private String VMClass;
        private String instanceSeries;
        private String instanceType;
        private String numberOfGPUs;
        private String GPUType;
        private String localSSD;
        private String datacenterLocation;
        private String committedUsage;

        private Builder() {
        }

        private Builder(GoogleCloudMainForm form) {
            this.productName = form.productName;
            this.numberOfInstances = form.numberOfInstances;
            this.operationSystem = form.operationSystem;
            this.VMClass = form.VMClass;
            this.instanceSeries = form.instanceSeries;
            this.instanceType = form.instanceType;
            this.numberOfGPUs = form.numberOfGPUs;
            this.GPUType = form.GPUType;
            this.localSSD = form.localSSD;
            this.datacenterLocation = form.datacenterLocation;
            this.committedUsage = form.committedUsage;
        }

        public Builder withProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder withNumberOfInstances(String numberOfInstances) {
            this.numberOfInstances = numberOfInstances;
            return this;
        }

        public Builder withOperationSystem(String operationSystem) {
            this.operationSystem = operationSystem;
            return this;
        }

        public Builder withVMClass(String VMClass) {
            this.VMClass = VMClass;
            return this;
        }

        public Builder withInstanceSeries(String instanceSeries) {
            this.instanceSeries = instanceSeries;
            return this;
        }

        public Builder withInstanceType(String instanceType) {
            this.instanceType = instanceType;
            return this;
        }

        public Builder withNumberOfGPUs(String numberOfGPUs) {
            this.numberOfGPUs = numberOfGPUs;
            return this;
        }

        public Builder withGPUType(String GPUType) {
            this.GPUType = GPUType;
            return this;
        }

        public Builder withLocalSSD(String localSSD) {
            this.localSSD = localSSD;
            return this;
        }

        public Builder withDatacenterLocation(String datacenterLocation) {
            this.datacenterLocation = datacenterLocation;
            return this;
        }

        public Builder withCommittedUsage(String committedUsage) {
            this.committedUsage = committedUsage;
            return this;
        }

        public GoogleCloudMainForm build() {
            GoogleCloudMainForm form = new GoogleCloudMainForm();
            form.setProductName(productName);
            form.setNumberOfInstances(numberOfInstances);
            form.setOperationSystem(operationSystem);
            form.setVMClass(VMClass);
            form.setInstanceSeries(instanceSeries);
            form.setInstanceType(instanceType);
            form.setNumberOfGPUs(numberOfGPUs);
            form.setGPUType(GPUType);
            form.setLocalSSD(localSSD);
            form.setDatacenterLocation(datacenterLocation);
            form.setCommittedUsage(committedUsage);
            return form;
        }
    }

    public Builder edit() {
        return new Builder(this);
    }

    public static Builder create() {
        return new Builder();
    }

    public String getProductName() {
        return productName;
    }

    public String getNumberOfInstances() {
        return numberOfInstances;
    }

    public String getOperationSystem() {
        return operationSystem;
    }

    public String getVMClass() {
        return VMClass;
    }

    public String getInstanceSeries() {
        return instanceSeries;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public String getNumberOfGPUs() {
        return numberOfGPUs;
    }

    public String getGPUType() {
        return GPUType;
    }

    public String getLocalSSD() {
        return localSSD;
    }

    public String getDatacenterLocation() {
        return datacenterLocation;
    }

    public String getCommittedUsage() {
        return committedUsage;
    }

    @Override
    public String toString() {
        return "GoogleCloudMainForm{" +
                "productName='" + productName + '\'' +
                ", numberOfInstances='" + numberOfInstances + '\'' +
                ", operationSystem='" + operationSystem + '\'' +
                ", VMClass='" + VMClass + '\'' +
                ", instanceSeries='" + instanceSeries + '\'' +
                ", instanceType='" + instanceType + '\'' +
                ", numberOfGPUs='" + numberOfGPUs + '\'' +
                ", GPUType='" + GPUType + '\'' +
                ", localSSD='" + localSSD + '\'' +
                ", datacenterLocation='" + datacenterLocation + '\'' +
                ", committedUsage='" + committedUsage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoogleCloudMainForm that = (GoogleCloudMainForm) o;
        return Objects.equals(productName, that.productName) && Objects.equals(numberOfInstances, that.numberOfInstances) &&
                Objects.equals(operationSystem, that.operationSystem) && Objects.equals(VMClass, that.VMClass) &&
                Objects.equals(instanceSeries, that.instanceSeries) && Objects.equals(instanceType, that.instanceType) &&
                Objects.equals(numberOfGPUs, that.numberOfGPUs) && Objects.equals(GPUType, that.GPUType) &&
                Objects.equals(localSSD, that.localSSD) && Objects.equals(datacenterLocation, that.datacenterLocation) &&
                Objects.equals(committedUsage, that.committedUsage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, numberOfInstances, operationSystem, VMClass, instanceSeries, instanceType,
                numberOfGPUs, GPUType, localSSD, datacenterLocation, committedUsage);
    }

    private void setProductName(String productName) {
        this.productName = productName;
    }

    private void setNumberOfInstances(String numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    private void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    private void setVMClass(String VMClass) {
        this.VMClass = VMClass;
    }

    private void setInstanceSeries(String instanceSeries) {
        this.instanceSeries = instanceSeries;
    }

    private void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    private void setNumberOfGPUs(String numberOfGPUs) {
        this.numberOfGPUs = numberOfGPUs;
    }

    private void setGPUType(String GPUType) {
        this.GPUType = GPUType;
    }

    private void setLocalSSD(String localSSD) {
        this.localSSD = localSSD;
    }

    private void setDatacenterLocation(String datacenterLocation) {
        this.datacenterLocation = datacenterLocation;
    }

    private void setCommittedUsage(String committedUsage) {
        this.committedUsage = committedUsage;
    }
}