/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fintract;

import fintract.Arch.Controller.TransactionController;
import fintract.Arch.View.ConsoleTableManager.Implementations.ConsoleTableManagerV1;
import fintract.Arch.View.ViewManager.ViewManager;
import fintract.DAOExecutor.TransactionDAOExecutor;
import fintract.Processors.Implementations.CMDAnalyzerServiceProcessorV1;
import fintract.Service.CMDServiceProcessorExecutor;
import fintract.Processors.Implementations.DAOProcessorV1;

/**
 *
 * @author hunter
 */
public class Integrator {
    public static void main(String[] args){
        CMDServiceProcessorExecutor cmd_processor = new CMDServiceProcessorExecutor(new CMDAnalyzerServiceProcessorV1());
        TransactionDAOExecutor daoExecutor = new TransactionDAOExecutor(new DAOProcessorV1());
        ViewManager view_manager = new ViewManager(new ConsoleTableManagerV1());
        TransactionController controller = new TransactionController(cmd_processor,daoExecutor, view_manager);
        controller.start();
    }
}
