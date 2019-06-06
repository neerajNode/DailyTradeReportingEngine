import java.util.List;
import java.util.Optional;

import com.reporting.enums.InstructionTypeEnum;
import com.reporting.models.Instruction;
import com.reporting.reporter.Report;
import com.reporting.utils.InputDataGenerator;

//Main class that starts the reporting engine
public class Runner {

	public static void main(String[] args) {
		try {
			// get sample data to process
			Optional<List<Instruction>> instructionsOptional = InputDataGenerator.generate();
			
			//generate reports in case instructions list is not null and not empty
			if (instructionsOptional.isPresent() && instructionsOptional.get().size()>0) {
				Report report = new Report(instructionsOptional.get());

				// print settlement reports for outgoing trades
				report.printSettlementReport(InstructionTypeEnum.BUY);

				// print settlement reports for incoming trades
				report.printSettlementReport(InstructionTypeEnum.SELL);

				// print rankings for outgoing trades
				report.printRankings(InstructionTypeEnum.BUY);

				// print rankings for incoming trades
				report.printRankings(InstructionTypeEnum.SELL);
			} else {
				System.err.println("No input instructions found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
