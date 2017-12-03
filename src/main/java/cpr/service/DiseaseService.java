package cpr.service;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cpr.dao.DiseaseRepository;
import cpr.model.Disease;
import cpr.model.Patient;

@Service
@Transactional
public class DiseaseService {

	private final DiseaseRepository diseaseRepository;

	public DiseaseService(DiseaseRepository diseaseRepository) {
		this.diseaseRepository = diseaseRepository;
	}

	public List<Disease> findAll() {
		List<Disease> diseases = new ArrayList<>();
		for (Disease disease : diseaseRepository.findAll()) {
			diseases.add(disease);
		}
		return diseases;
	}

	public Disease findDisease(int id) {
		return diseaseRepository.findOne(id);
	}

	public void save(Disease disease) {
		diseaseRepository.save(disease);
	}

	public void delete(int id) {
		diseaseRepository.delete(id);
	}

	public List<Disease> getDiseaseByPatient(Patient patient) throws Exception {
		List<Disease> diseaseList = diseaseRepository.findByPatient(patient);
		return diseaseList;
	}

	public boolean validator(Disease disease) {
		int count = 0;
		for (int i = 0; i < disease.getDescription().length(); i++) {
			int c = disease.getDescription().charAt(i);
			if (((c >= 65) && (c <= 90)) || ((c >= 97) && (c <= 122))) {
				count++;
			}
		}
		
		if (count == 0) {
			return false;
		}

		if (disease.getStartDate() == "") {
			return false;
		}
		
		if (disease.getEndDate() != "") {
			try {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date date_start = format.parse(disease.getStartDate());
				Date date_end = format.parse(disease.getEndDate());
				int comp = date_start.compareTo(date_end);
				if (comp != -1) {
					return false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
}
