package com.anant.performance.demo.contoller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component
public class BubbleSort {

	static class Array {
		private int[] input;
	}

	public final String JSON_PATH = "*array.json";

	int[] arr;

	private ResourceLoader resourceLoader;

	@Autowired
	public BubbleSort(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@PostConstruct
	public void init() throws FileNotFoundException {

		try {

			
			Resource resource = resourceLoader.getResource("classpath:array.json");
			InputStream fileStream = resource.getInputStream();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(fileStream, "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String sCurrentLine = null;

			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine);
			}
			JsonObject jobj = new Gson().fromJson(sb.toString(), JsonObject.class);
			JsonArray array = jobj.get("numbers").getAsJsonArray();
			arr = new int[array.size()];

			for (int i = 0; i < array.size(); i++) {
				arr[i] = array.get(i).getAsInt();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void runBubbleSort() {
		int n = arr.length;
		System.out.println(n);
		int temp = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {
				if (arr[j - 1] > arr[j]) {
					// swap elements
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}

			}
		}
	}
}
