/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fintract.Service;

import fintract.Utility.ENUM_TYPES.COMMAND_TYPE;
import fintract.Processors.CMDAnalyzerServiceProcessor;
import static fintract.Utility.ENUM_TYPES.COMMAND_TYPE.NO_RESOURCES;

/**
 *
 * @author hunter
 */
public class CMDServiceProcessorExecutor{
    private final CMDAnalyzerServiceProcessor cmd_processor;
    
    public CMDServiceProcessorExecutor(CMDAnalyzerServiceProcessor processor){
        this.cmd_processor = processor;
    }

    public COMMAND_TYPE processType(String[] cmd_data) {
        return cmd_processor.processType(cmd_data);
    }
    
    public COMMAND_TYPE addTypeCMD(String[] cmd_data) {
        return cmd_processor.addTypeProcess(cmd_data);
    }

    public COMMAND_TYPE delete(String[] cmd_data) {
        return cmd_processor.deleteTypeProcess(cmd_data);
    }

    public COMMAND_TYPE edit(String[] cmd_data) {
        return cmd_processor.updateTypeProcess(cmd_data);
    }
    
    public COMMAND_TYPE fetchDataForReport(String[] cmd_data) {
        return cmd_processor.fetchTypeProcess(cmd_data);
    }

    public COMMAND_TYPE generateDocs(String[] cmd_data) {
        //return cmd_processor.DocsGenerateTypeProcess(cmd_data); 
        return NO_RESOURCES;
    }
    
    public String[] getData(){
        return cmd_processor.getData();
    }
}