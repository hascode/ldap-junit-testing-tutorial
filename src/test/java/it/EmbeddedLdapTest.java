package it;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Rule;
import org.junit.Test;
import org.zapodot.junit.ldap.EmbeddedLdapRule;
import org.zapodot.junit.ldap.EmbeddedLdapRuleBuilder;

import com.unboundid.ldap.sdk.LDAPInterface;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchScope;

/**
 * Test using objectclass: org.zapodot:embedded-ldap-junit
 */
public class EmbeddedLdapTest {
	public static final String DOMAIN_DSN = "dc=example,dc=com";
	@Rule
	public EmbeddedLdapRule embeddedLdapRule = EmbeddedLdapRuleBuilder.newInstance().usingDomainDsn(DOMAIN_DSN)
			.importingLdifs("users-import.ldif").build();

	@Test
	public void testLdapConnection() throws Exception {
		final LDAPInterface ldapConnection = embeddedLdapRule.ldapConnection();
		final SearchResult searchResult = ldapConnection.search(DOMAIN_DSN, SearchScope.SUB, "(objectClass=person)");
		assertThat(3, equalTo(searchResult.getEntryCount()));
	}
}
