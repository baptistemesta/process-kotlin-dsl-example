import com.bonitasoft.engine.dsl.process.process

process("MyProcess", "1.0") {
    automaticTask("Step1"){
        transition("gate")
    }
    exclusiveGateway("gate"){
        transition("Step2") {
            default = true
        }
        transition("Step3")
    }
    automaticTask("Step2") { }
    automaticTask("Step3") { }
}