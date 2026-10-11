package com.example.smartcomplaintmanagment;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id).orElse(null);
    }

    public Complaint createComplaint(Complaint complaint) {
        complaint.setStatus("Pending");
        return complaintRepository.save(complaint);
    }

    public Complaint updateComplaint(Long id, Complaint complaint) {
        Complaint existing = complaintRepository.findById(id).orElse(null);

        if (existing != null) {
            existing.setName(complaint.getName());
            existing.setEmail(complaint.getEmail());
            existing.setSubject(complaint.getSubject());
            existing.setDescription(complaint.getDescription());
            existing.setStatus(complaint.getStatus());

            return complaintRepository.save(existing);
        }

        return null;
    }

    public void deleteComplaint(Long id) {
        complaintRepository.deleteById(id);
    }
}