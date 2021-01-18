package transfer.npa;

public class MappingDept {

	// 發布單位Mapping
	public static String mappingNPAPubUnitDN(String dept) {
		String PubUnitDN3 = "ou=A2M00,ou=organization,o=npa,c=tw";

		if ("1".equals(dept)) {
			// 1嘉聯科技
			PubUnitDN3 = "ou=A2M00,ou=organization,o=npa,c=tw";
		} else if ("2".equals(dept)) {
			// 2臺長室
			PubUnitDN3 = "ou=A2M21,ou=A2M00,ou=organization,o=npa,c=tw";
		} else if ("3".equals(dept)) {
			// 3副臺長室
			PubUnitDN3 = "ou=A2M00,ou=organization,o=npa,c=tw";
		} else if ("4".equals(dept)) {
			// 4秘書
			PubUnitDN3 = "ou=A2MC1,ou=A2M00,ou=organization,o=npa,c=tw";
		} else if ("5".equals(dept)) {
			// 5企劃科
			PubUnitDN3 = "ou=A2MK1,ou=A2M00,ou=organization,o=npa,c=tw";
		} else if ("6".equals(dept)) {
			// 6節目科
			PubUnitDN3 = "ou=A2MB1,ou=A2M00,ou=organization,o=npa,c=tw";
		} else if ("7".equals(dept)) {
			// 7新聞科
			PubUnitDN3 = "ou=A2ME1,ou=A2M00,ou=organization,o=npa,c=tw";
		} else if ("8".equals(dept)) {
			// 8工務科
			PubUnitDN3 = "ou=A2MG1,ou=A2M00,ou=organization,o=npa,c=tw";
		} else if ("9".equals(dept)) {
			// 9秘書室
			PubUnitDN3 = "ou=A2MC1,ou=A2M00,ou=organization,o=npa,c=tw";
		} else if ("10".equals(dept)) {
			// 10人事室
			PubUnitDN3 = "ou=A2MF1,ou=A2M00,ou=organization,o=npa,c=tw";
		} else if ("11".equals(dept)) {
			// 11主計室
			PubUnitDN3 = "ou=A2MD1,ou=A2M00,ou=organization,o=npa,c=tw";
		} else if ("12".equals(dept)) {
			// 12督察員
			PubUnitDN3 = "ou=A2M31,ou=A2M00,ou=organization,o=npa,c=tw";
		} else if ("13".equals(dept)) {
			// 13臺北分臺
			PubUnitDN3 = "ou=A2MS1,ou=A2M00,ou=organization,o=npa,c=tw";
		} else if ("14".equals(dept)) {
			// 14新竹分臺
			PubUnitDN3 = "ou=A2MT1,ou=A2M00,ou=organization,o=npa,c=tw";
		} else if ("15".equals(dept)) {
			// 15臺中分臺
			PubUnitDN3 = "ou=A2MU1,ou=A2M00,ou=organization,o=npa,c=tw";
		} else if ("16".equals(dept)) {
			// 16臺南分臺
			PubUnitDN3 = "ou=A2MV1,ou=A2M00,ou=organization,o=npa,c=tw";
		} else if ("17".equals(dept)) {
			// 17高雄分臺
			PubUnitDN3 = "ou=A2MW1,ou=A2M00,ou=organization,o=npa,c=tw";
		} else if ("18".equals(dept)) {
			// 18宜蘭分臺
			PubUnitDN3 = "ou=A2MY1,ou=A2M00,ou=organization,o=npa,c=tw";
		} else if ("19".equals(dept)) {
			// 19花蓮分臺
			PubUnitDN3 = "ou=A2MX1,ou=A2M00,ou=organization,o=npa,c=tw";
		} else if ("20".equals(dept)) {
			// 20臺東分臺
			PubUnitDN3 = "ou=A2MZ1,ou=A2M00,ou=organization,o=npa,c=tw";
		} else if ("21".equals(dept)) {
			// 21全國治安交通網
			PubUnitDN3 = "ou=A2M00,ou=organization,o=npa,c=tw";
		}
		return PubUnitDN3;

	}
}