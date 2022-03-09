package interfaces.objects;

import org.openqa.selenium.By;

public interface WebBaseElements {

	default By id(String idElemento) {
		return By.id(idElemento);
	}
	default By xpath(String elementSelector) {
		return By.xpath(elementSelector);
	}
	default By tag(String elementSelector) {
		return By.tagName(elementSelector);
	}
	default By css(String elementSelector) {
		return By.cssSelector(elementSelector);
	}
	default By link(String elementSelector) {
		return By.linkText(elementSelector);
	}
	default By partialLink(String elementSelector) {
		return By.partialLinkText(elementSelector);
	}
	default By className(String elementSelector) {
		return By.className(elementSelector);
	}
	default By name(String elementSelector) {
		return By.name(elementSelector);
	}
}
