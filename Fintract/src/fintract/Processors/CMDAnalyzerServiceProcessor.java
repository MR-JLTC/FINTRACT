/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fintract.Processors;

import fintract.Utility.ENUM_TYPES.COMMAND_TYPE;

/**
 *
 * @author hunter
 */
public interface CMDAnalyzerServiceProcessor {
    COMMAND_TYPE processType(String[] cmd_data);
    COMMAND_TYPE addTypeProcess(String[] cmd_data);
    COMMAND_TYPE deleteTypeProcess(String[] cmd_data);
    COMMAND_TYPE updateTypeProcess(String[] cmd_data);
    COMMAND_TYPE fetchTypeProcess(String[] cmd_data);
    COMMAND_TYPE DocsGenerateTypeProcess(String[] cmd_data);
    String[] getData();
}
