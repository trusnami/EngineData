package Model;

public class FreezeFrame1 {


    int id = 0;
    //车速
    double Speed = 0;
    //发动机转速
    double EngineSpeed = 0;
    //冷却液温度
    double CoolantTemperature = 0;
    //加速踏板
    double AccelerationPedal1 = 0;
    //故障码
    String FaultCode = "0-0";
    //环境温度
    double AmbientTemperature = -40;
    //蓄电池电压
    double BatteryVoltage = 0;
    //轨压
    double RailPressure = 0;
    //计算负荷值
    double ComputationalLoadValue = 0;
    //大气压力
    double AtmosphericPressure = 0;
    //发动机负荷
    double EngineLoad = 0;
    //进气压力
    double ManifoldAbsolutePressure = 0;
    //扭矩模式
    double TorqueMode = 255;
    //冻结帧长度
     int FreezeFrameLength = 15;
    //进气温度
    double IntakeAirTemperature  = -120.375;
    //油门踏板1开度
    double AcceleratorPedal1Opening = 0;
    //油门踏板2开度
    double AcceleratorPedal2Opening = 0;
    //进气流量
    double IntakeFlow = 269.9;
    //油门踏板传感器1信号
    double AcceleratorPedalSensor1Signal = 0;
    //监控模块电压
    double MonitoringModuleVoltage = 23.94000053;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSpeed() {
        return Speed;
    }

    public void setSpeed(double speed) {
        Speed = speed;
    }

    public double getEngineSpeed() {
        return EngineSpeed;
    }

    public void setEngineSpeed(double engineSpeed) {
        EngineSpeed = engineSpeed;
    }

    public double getCoolantTemperature() {
        return CoolantTemperature;
    }

    public void setCoolantTemperature(double coolantTemperature) {
        CoolantTemperature = coolantTemperature;
    }

    public double getAccelerationPedal1() {
        return AccelerationPedal1;
    }

    public void setAccelerationPedal1(double accelerationPedal1) {
        AccelerationPedal1 = accelerationPedal1;
    }

    public String getFaultCode() {
        return FaultCode;
    }

    public void setFaultCode(String faultCode) {
        FaultCode = faultCode;
    }

    public double getAmbientTemperature() {
        return AmbientTemperature;
    }

    public void setAmbientTemperature(double ambientTemperature) {
        AmbientTemperature = ambientTemperature;
    }

    public double getBatteryVoltage() {
        return BatteryVoltage;
    }

    public void setBatteryVoltage(double batteryVoltage) {
        BatteryVoltage = batteryVoltage;
    }

    public double getRailPressure() {
        return RailPressure;
    }

    public void setRailPressure(double railPressure) {
        RailPressure = railPressure;
    }

    public double getComputationalLoadValue() {
        return ComputationalLoadValue;
    }

    public void setComputationalLoadValue(double computationalLoadValue) {
        ComputationalLoadValue = computationalLoadValue;
    }

    public double getAtmosphericPressure() {
        return AtmosphericPressure;
    }

    public void setAtmosphericPressure(double atmosphericPressure) {
        AtmosphericPressure = atmosphericPressure;
    }

    public double getEngineLoad() {
        return EngineLoad;
    }

    public void setEngineLoad(double engineLoad) {
        EngineLoad = engineLoad;
    }

    public double getManifoldAbsolutePressure() {
        return ManifoldAbsolutePressure;
    }

    public void setManifoldAbsolutePressure(double manifoldAbsolutePressure) {
        ManifoldAbsolutePressure = manifoldAbsolutePressure;
    }

    public double getTorqueMode() {
        return TorqueMode;
    }

    public void setTorqueMode(double torqueMode) {
        TorqueMode = torqueMode;
    }

    public int getFreezeFrameLength() {
        return FreezeFrameLength;
    }

    public void setFreezeFrameLength(int freezeFrameLength) {
        FreezeFrameLength = freezeFrameLength;
    }

    public double getIntakeAirTemperature() {
        return IntakeAirTemperature;
    }

    public void setIntakeAirTemperature(double intakeAirTemperature) {
        IntakeAirTemperature = intakeAirTemperature;
    }

    public double getAcceleratorPedal1Opening() {
        return AcceleratorPedal1Opening;
    }

    public void setAcceleratorPedal1Opening(double acceleratorPedal1Opening) {
        AcceleratorPedal1Opening = acceleratorPedal1Opening;
    }

    public double getAcceleratorPedal2Opening() {
        return AcceleratorPedal2Opening;
    }

    public void setAcceleratorPedal2Opening(double acceleratorPedal2Opening) {
        AcceleratorPedal2Opening = acceleratorPedal2Opening;
    }

    public double getIntakeFlow() {
        return IntakeFlow;
    }

    public void setIntakeFlow(double intakeFlow) {
        IntakeFlow = intakeFlow;
    }

    public double getAcceleratorPedalSensor1Signal() {
        return AcceleratorPedalSensor1Signal;
    }

    public void setAcceleratorPedalSensor1Signal(double acceleratorPedalSensor1Signal) {
        AcceleratorPedalSensor1Signal = acceleratorPedalSensor1Signal;
    }

    public double getMonitoringModuleVoltage() {
        return MonitoringModuleVoltage;
    }

    public void setMonitoringModuleVoltage(double monitoringModuleVoltage) {
        MonitoringModuleVoltage = monitoringModuleVoltage;
    }

    public void printAttribute(){
        System.out.println(id +" "+Speed+" "+EngineSpeed+" "+CoolantTemperature+" ");
    }

}
