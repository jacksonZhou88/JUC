package org.springframework.tests;

import org.junit.Test;

import java.util.*;

public class TestComparator {

	@Test
	public void testComparator() {
		List<String> dateList = new ArrayList<>();
		dateList.add("640T001·20181205-401");
		dateList.add("630T001·20181205-303");
		dateList.add("630T001·20190105-303");
		dateList.add("630T001·20170205-303");

		Collections.sort(dateList, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				String[] arr1 = o1.split("·|-");
				String[] arr2 = o2.split("·|-");
				Date date1 = org.springframework.tests.DateUtils.formatDateStr(arr1[1]);
				Date date2 = org.springframework.tests.DateUtils.formatDateStr(arr2[1]);
				if (date1.after(date2)) {
					return 1;
				} else {
					return -1;
				}
			}
		});

		System.out.println(dateList);

	}

}
