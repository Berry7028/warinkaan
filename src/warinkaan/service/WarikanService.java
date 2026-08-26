package warinkaan.service;

import java.util.ArrayList;
import java.util.List;

import warinkaan.model.BurdenResult;
import warinkaan.model.Participant;
import warinkaan.model.WarikanSession;

public class WarikanService {
	private final List<WarikanSession> sessions = new ArrayList<>();

	public void createSession(WarikanSession session) {
		sessions.add(session);
	}

	public List<WarikanSession> getSessions() {
		return sessions;
	}

	private WarikanSession findSessionById(int sessionId) {
		for (WarikanSession session : sessions) {
			if (session.getSessionId() == sessionId) {
				return session;
			}
		}
	}

	public boolean deleteSession(int sessionId) {
		WarikanSession session = findSessionById(sessionId);

		if (session == null) {
			return false;
		}

		sessions.remove(session);
		return true;

	}

	public boolean addParticipant(int sessionId, Participant participant) {
		WarikanSession session = findSessionById(sessionId);

		if (session == null) {
			return false;
		}

		session.getParticipants().add(participant);
		return true;

	}

	public boolean updateParticipant(int sessionId, int participantId, String newName, int newWeight) {
		WarikanSession session = findSessionById(sessionId);

		if (session == null) {
			return false;
		}

		Participant participant = findParticipantById(session, participantId);

		if (participant == null) {
			return false;
		}

		participant.setName(newName);
		participant.setWeight(newWeight);

		return true;
	}

	public boolean deleteParticipant(int sessionId, int participantId) {
		WarikanSession session = findSessionById(sessionId);

		if (session == null) {
			return false;
		}

		Participant participant = findParticipantById(session, participantId);

		if (participant == null) {
			return false;
		}

		session.getParticipants().remove(participant);

		return true;
	}

	public List<BurdenResult> calculateBurdens(int sessionId) {
		List<BurdenResult> results = new ArrayList<>();

		WarikanSession session = findSessionById(sessionId);

		if (session == null || session.getParticipants().isEmpty()) {
			return results;
		}

		int totalWeight = 0;

		for (Participant participant : session.getParticipants()) {
			totalWeight += participant.getWeight();
		}

		if (totalWeight <= 0) {
			return results;
		}

		int distributedAmount = 0;
		List<Participant> participants = session.getParticipants();

		for (int i = 0; i < participants.size(); i++) {
			Participant participant = participants.get(i);
			int burdenAmount;

			if (i == participants.size() - 1) {
				burdenAmount = session.getTotalAmount() - distributedAmount;
			} else {
				burdenAmount = (int) ((long) session.getTotalAmount() * participant.getWeight() / totalWeight);

				distributedAmount += burdenAmount;
			}

			BurdenResult result = new BurdenResult(participant.getParticipantId(), participant.getName(), burdenAmount);

			results.add(result);
		}

		return results;
	}
}
