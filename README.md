# A Daily Trade Reporting Engine (Compiled with Java 1.8)

# Used to generate a report that shows
* Amount in USD settled incoming everyday
* Amount in USD settled outgoing everyday
* Ranking of entities based on incoming and outgoing amount. Eg: If entity foo instructs the highest
  amount for a buy instruction, then foo is rank 1 for outgoing

# Sample Input
Entity|Buy/Sell|AgreedFx|Currency|InstructionDate|SettlementDate|Units|Price per unit

"foo1","B",0.50,"AED","07 Jun 2019","08 Jun 2019",100,100.25

"bar1","S",0.70,"INR","01 Jan 2019","03 Jan 2019",200,90.25

"foo2","S",0.60,"GBP","01 May 2019","02 May 2019",100,100.25

"bar2","B",0.70,"SAR","01 Jan 2016","02 Jan 2016",200,90.25

"foo3","B",0.50,"GBP","09 May 2018","10 May 2018",100,100.25

"bar3","S",0.99,"INR","01 Jan 2019","03 Jan 2019",200,90.25

"foo4","B",0.80,"GBP","07 Jun 2019","08 Jun 2019",100,100.25

"bar4","S",0.90,"INR","01 Feb 2019","03 Feb 2019",200,90.25

"foo5","B",0.55,"GBP","07 Jun 2019","08 Jun 2019",100,100.25

"bar5","S",0.98,"INR","16 Jun 2019","17 Jun 2019",200,90.25

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

# Glossary
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
