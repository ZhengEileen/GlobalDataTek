package enrollment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enrollmentRecord.EnrollmentRecord;

public class Enrollment {

	public static void processEnrollmentFile(String inputFile) throws IOException {
		Map<String, List<EnrollmentRecord>> insuranceData = new HashMap<>();
		String output = new File(inputFile).getParent();

		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		String line;
		boolean header = true;

		while ((line = br.readLine()) != null) {
			if (header) {
				header = false;
				continue;
			}

			String[] fields = line.split(",");
			String userId = fields[0];
			String firstName = fields[1];
			String lastName = fields[2];
			int version = Integer.parseInt(fields[3]);
			String insuranceCompany = fields[4];

			EnrollmentRecord record = new EnrollmentRecord(userId, firstName, lastName, version, insuranceCompany);

			if (!insuranceData.containsKey(insuranceCompany)) {
				insuranceData.put(insuranceCompany, new ArrayList<>());
			}
			insuranceData.get(insuranceCompany).add(record);
		}
		br.close();

		for (Map.Entry<String, List<EnrollmentRecord>> entry : insuranceData.entrySet()) {
			List<EnrollmentRecord> records = entry.getValue();

			Map<String, EnrollmentRecord> uniqueRecords = new HashMap<>();
			for (EnrollmentRecord record : records) {
				String userId = record.getUserId();
				if (!uniqueRecords.containsKey(userId)
						|| record.getVersion() > uniqueRecords.get(userId).getVersion()) {
					uniqueRecords.put(userId, record);
				}
			}

			List<EnrollmentRecord> uniqueRecordsList = new ArrayList<>(uniqueRecords.values());

			Collections.sort(uniqueRecordsList, new Comparator<EnrollmentRecord>() {

				@Override
				public int compare(EnrollmentRecord o1, EnrollmentRecord o2) {
					int lastNameComparison = o1.getLastName().compareTo(o2.getLastName());
					if (lastNameComparison != 0) {
						return lastNameComparison;
					} else {
						return o1.getFirstName().compareTo(o2.getFirstName());
					}
				}
			});

			String outputFile = Paths.get(output, entry.getKey().replace(" ", "_") + ".csv").toString();
			FileWriter writer = new FileWriter(outputFile);
			writer.append("User Id,First Name,Last Name,Version,Insurance Company\n");
			for (EnrollmentRecord record : uniqueRecordsList) {
				writer.append(record.getUserId()).append(',').append(record.getFirstName()).append(',')
						.append(record.getLastName()).append(',').append(String.valueOf(record.getVersion()))
						.append(',').append(record.getInsuranceCompany()).append('\n');
			}
			writer.close();
		}
	}
}
