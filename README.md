# ATeamProject
CS 400 ATeamProject

TeamMembers:

Zhengzhi Chen  zchen665@wisc.edu   xteam168

Jiahe Wang     jwang2249@wisc.edu  xteam136

Jiong Chen     jchen672@wisc.edu   xteam134

Shuyi Li       sli524@wisc.edu     xteam189

Jitian Liu     jliu742@wisc.edu    xteam129

special instructions -
  The date should be entered in the form of "yyyy-mm-dd". The program also accepts the form of "yyyy-m-d" (such as "2020-1-1 instead of 2020-01-01" also works.)
  The date is expected to be complited. To be more specific, it should includ all three parts of "year" "month" and "day" and seperated by "-". If only a year or month is included and other parts is missing there will be an ArrayOutOfBoundsException. We resolved it at first by replacing it with "error" but eventually we decided to let it throw the exception. THIS IS NOT A BUG.
  The farm is expected to be in form of "Farm #" or "farm #" or "FARM #". The letter case matters, but if there is space between the number and the word farm does not.
  The weight should be in the same range as doubles in java. 
  
  If there are any missing data from the input text, for example, "-" or just " "(blank) would be detected as missing data and would be recorded as a String "missing" in the data structure. All data in the wrong format such as "ASDGSDHN" for farmID would be recorded as "error."
 
  there is a special rule about output files. instruction is listed on the gui.
  
bug report: 
  1. can only read one file a time  ---------resolved by take files as a list and process one by one
  2. after reading files, the data builds up with new data.---------resolved by adding clear data button option
  3. unable to handle error files -------- edited error handling by exception throwing and catch-- resolved
  4. monthly report  remains what it is for initially requested year and month even later an invalid year is reqeusted-- unresolved
  5. processing farmid, weight, those labels as data. -------- filtered in File_manager.---resolved
  
