package model;

import java.util.*;

public class PostKoukanLogic {
	public void butsu(PostKoukan koukan, List<PostKoukan> koukanList) {
		koukanList.add(0, koukan);
	}
}
