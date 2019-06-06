# A Daily Trade Reporting Engine (Compiled with Java 1.8)

# Used to generate a report that shows
* Amount in USD settled incoming everyday
* Amount in USD settled outgoing everyday
* Ranking of entities based on incoming and outgoing amount. Eg: If entity foo instructs the highest
  amount for a buy instruction, then foo is rank 1 for outgoing

# Sample Input
Entity|Buy/Sell|AgreedFx|Currency|InstructionDate|SettlementDate|Units|Price per unit

foo,B,0.50,SGP,01 Jan 2016,02 Jan 2016,200,100.25
bar,S,0.22,AED,05 Jan 2016,07 Jan 2016,450,150.5

# Sample Output
*** Settlement Report Outgoing ***

Settlement Date: 2019-06-10 | Amount Settled: 13533.7500 USD
Settlement Date: 2018-05-10 | Amount Settled: 5012.500 USD
Settlement Date: 2019-06-09 | Amount Settled: 5012.500 USD
Settlement Date: 2016-01-03 | Amount Settled: 12635.000 USD

*** Settlement Report Incoming ***

Settlement Date: 2019-02-04 | Amount Settled: 16245.000 USD
Settlement Date: 2019-01-03 | Amount Settled: 30504.5000 USD
Settlement Date: 2019-05-02 | Amount Settled: 6015.000 USD
Settlement Date: 2019-06-17 | Amount Settled: 17689.0000 USD

*** Ranking Report Outgoing ***

Settlement Date: 2019-06-10
Rank:1 | Entity:foo4 | Amount:8020.000 USD
Rank:2 | Entity:foo5 | Amount:5513.7500 USD

Settlement Date: 2018-05-10
Rank:1 | Entity:foo3 | Amount:5012.500 USD

Settlement Date: 2019-06-09
Rank:1 | Entity:foo1 | Amount:5012.500 USD

Settlement Date: 2016-01-03
Rank:1 | Entity:bar2 | Amount:12635.000 USD

*** Ranking Report Incoming ***

Settlement Date: 2019-02-04
Rank:1 | Entity:bar4 | Amount:16245.000 USD

Settlement Date: 2019-01-03
Rank:1 | Entity:bar3 | Amount:17869.5000 USD
Rank:2 | Entity:bar1 | Amount:12635.000 USD

Settlement Date: 2019-05-02
Rank:1 | Entity:foo2 | Amount:6015.000 USD

Settlement Date: 2019-06-17
Rank:1 | Entity:bar5 | Amount:17689.0000 USD

# Gloassary
* Instruction: An instruction to buy or sell
*  Entity: A financial entity whose shares are to be bought or sold
* Instruction Date: Date on which the instruction was sent to JP Morgan by various clients
* Settlement Date: The date on which the client wished for the instruction to be settled with respect
to Instruction Date
* Buy/Sell flag:
  B – Buy – outgoing
  S – Sell – incoming
* Agreed Fx is the foreign exchange rate with respect to USD that was agreed
* Units: Number of shares to be bought or sold
