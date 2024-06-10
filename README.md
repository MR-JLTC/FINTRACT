![headerlogo](https://github.com/MR-JLTC/FINTRACT/assets/168248719/6f99af16-227a-4857-bddf-d01850f7e878)

### System Availability
[![Static Badge](https://img.shields.io/badge/Termux%20-v1.0beta%20-g)](FINTRACT/releases/termux)
[![Static Badge](https://img.shields.io/badge/Windows10%20-v1.0beta%20-blue)](FINTRACT/releases/windows)

### Description
Unlock financial clarity with our CLI-powered personal financial tracker. Effortlessly log income and expenses, charting your financial journey with precision and control. Take command of your finances, one command at a time.


### Termux Installation
1. Download the zip file, then extract it.
2. After extracting the file, run the following command:
```
cd FINTRACT
bash run.sh
```

### Windows Installation
1. Download the system installer file
2. Run the installer
3. Click the `.exe` file

### Available Commands:
```
a acnt <account_name>                                                                  |- Add an account for a collection of financial transaction
u acnt <account_name>                                                                  |- Use an existing account for a collection of financial transaction
a exp <description> <amount> [--date mm-dd-yyyy] or [--date today]                     |- To record an expense 
a inc <description> <amount> [--date mm-dd-yyyy]  or [--date today]                    |- To record income
d exp <exp_id>                                                                         |- To delete a recorded expense
d inc <inc_id>                                                                         |- To delete a recorded income
e exp <expense_id> <description> <amount> [--date mm-dd-yyyy] or [--date today]        |- To edit a recorded expense
e inc <income_id> <description> <amount> [--date mm-dd-yyyy]  or [--date today]        |- To edit a recorded income                                                                    
s exp [--month <month>]                                                                |- Displays a summary of expenses, optionally by month
s inc [--month <month>]                                                                |- Displays a summary of incomes, optionally by month      
s exp -all                                                                             |- Displays all of recorded expenses within current year
s inc -all                                                                             |- Displays all of recorded incomes within current year
s -all tsn                                                                             |- Displays all of recorded expenses and incomes, tsn stands for transaction
gd [--month <month>]                                                                   |- Generates a printable docs for the summarize expenses, optionally by month 
gd -all                                                                                |- Generates a printable docs for all the recorded personal transactions
gd acnt <account_name> [--month <month>]                                               |- Creates a printable docs for specified recorded personal transactions by month
gd acnt <account_name> -all                                                            |- Generates a printable docs for all the recorded personal transactions within that account 
h or help                                                                              |- Displays command list
h -wd or help -wd                                                                      |- Displays command list with description
x or q                                                                                 |- Exits the application
```

### Example Command Usage
`<--USE OF AN ACCOUNT-->`
```
u acnt SchoolDays
```

`<--ADDING-->`
```
a acnt SchoolDays
a exp Transportation 40 --date 6-10-2024
a exp Transportation 40 --date today
a inc coding 5000 --date 6-10-2024
a inc coding 5000 --date today
```

`<--DELETIONS-->`
```
d exp 3
d inc 3
```

`<--EDITNG-->`
```
e exp 3 Transportation 50 --date 6-10-2024
e exp 3 Transportation 50 --date today
e inc 3 Transportation 50 --date 6-10-2024
e inc 3 Transportation 50 --date today
```

`<--SUMMARY REPORT IN TABLE FORMAT-->`
```
s exp --month 7
s exp --month Sept
s inc --month 7
s inc --month Sept
```

`<--GENERATING A PRINTABLE DOCUMENTS-->`
```
gd --month 7
gd --month Sept
gd acnt SchoolDays --month 7
gd acnt SchoolDays --month Sept
gd acnt SchooDays -all
```

### Output View
