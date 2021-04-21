/*
 * Copyright (c) 2020 Ubique Innovation AG <https://www.ubique.ch>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package org.dpppt.android.sdk.internal.backend.models;

import android.content.Context;

import org.dpppt.android.sdk.InteroperabilityMode;
import org.dpppt.android.sdk.internal.AppConfigManager;

import java.util.ArrayList;
import java.util.List;

public class GaenSecondDay {

	private GaenKey delayedKey;
	private int fake;
	private List<String> countries;

	public GaenSecondDay(GaenKey gaenKey, Context context) {
		this.delayedKey = gaenKey;
		this.fake = gaenKey.fake;
		this.countries = new ArrayList<>();

		this.countries.add("MT");
		AppConfigManager appConfigManager = AppConfigManager.getInstance(context);
		boolean interopPossible = appConfigManager.isInteropPossible();
		if(interopPossible){
			int interopMode = appConfigManager.getInteropMode();
			switch (interopMode){
				case InteroperabilityMode.EU:{
					countries.addAll(appConfigManager.getInteropEuropeanCountries());
					break;
				}
				case InteroperabilityMode.COUNTRIES_UPDATE_PENDING:
				case InteroperabilityMode.COUNTRIES: {
					countries.addAll(appConfigManager.getInteropSelectedCountries());
					break;
				}
				default:{
					break;
				}
			}
		}
	}

	public Integer isFake() {
		return this.fake;
	}

	public GaenKey getDelayedKey() {
		return delayedKey;
	}

	public List<String> getCountries() { return countries; }

	public void setCountries(List<String> countries) { this.countries = countries; }
}
