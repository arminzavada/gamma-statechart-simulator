package com.triad.school.gamma.simulator.query

import java.util.Map.Entry

class DummyEntry<T> implements Entry<String, T> {
	val String key
	var T value
	
	new (String key, T value) {
		this.key = key
		this.value = value
	}
	
	override getKey() {
		key
	}
	
	override getValue() {
		value
	}
	
	override setValue(T arg) {
		value = arg
	}
}