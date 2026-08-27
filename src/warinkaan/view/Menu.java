package warinkaan.view;

import java.util.List;

import warinkaan.model.BurdenResult;
import warinkaan.model.Participant;
import warinkaan.model.WarikanSession;

public class Menu {

	public static void showMainMenu() {
		System.out.println("わりんかーん");
		System.out.println("1. セッション一覧");
		System.out.println("2. セッション作成");
		System.out.println("3. セッション編集");
		System.out.println("4. セッション削除");
		System.out.println("0. 削除");
	}

	public static void showSessions(List<WarikanSession> sessions) {
		if (sessions.isEmpty()) {
			System.out.println("セッションはありません");
			return;
		}
		for (WarikanSession session : sessions) {
			System.out.println("ID: " + session.getSessionId() + "合計金額: " + session.getTotalAmount() + "円");

		}
	}

	public static void showParticipants(List<Participant> participants) {
		if (participants.isEmpty()) {
			System.out.println("参加者はいません");
			return;
		}
		for (Participant participant : participants) {
			System.out.println("ID: " + participant.getParticipantId() + "名前: " + participant.getName() + "重み: "
					+ participant.getWeight());
		}
	}

	public static void showResult(List<BurdenResult> results) {
		if (results.isEmpty()) {
			System.out.println("結果が存在しません");
			return;
		}

		for (BurdenResult burdenResult : results) {
			System.out.println(burdenResult.getParticipantName() + ": " + burdenResult.getBurdenAmount() + "円");
		}

	}

}
