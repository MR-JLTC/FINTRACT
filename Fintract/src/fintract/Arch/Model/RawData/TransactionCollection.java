/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package fintract.Arch.Model.RawData;

import java.util.ArrayList;

/**
 *
 * @author hunter
 */
public record TransactionCollection(String acntName,
        ArrayList<TransactionType> IncomeList,
        ArrayList<TransactionType> ExpenseList) {}
