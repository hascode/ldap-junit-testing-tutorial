package it;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import javax.naming.NamingEnumeration;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.SortControl;

import org.apache.directory.server.annotations.CreateLdapServer;
import org.apache.directory.server.annotations.CreateTransport;
import org.apache.directory.server.core.annotations.ApplyLdifFiles;
import org.apache.directory.server.core.annotations.CreateDS;
import org.apache.directory.server.core.annotations.CreatePartition;
import org.apache.directory.server.core.integ.AbstractLdapTestUnit;
import org.apache.directory.server.core.integ.FrameworkRunner;
import org.apache.directory.server.integ.ServerIntegrationUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test using apacheds
 */
@RunWith(FrameworkRunner.class)
@CreateLdapServer(transports = { @CreateTransport(protocol = "LDAP") })
@CreateDS(allowAnonAccess = true, partitions = {
		@CreatePartition(name = "Example Partition", suffix = "dc=example,dc=com") })
@ApplyLdifFiles("users-import.ldif")
public class ApacheDsLdapTest extends AbstractLdapTestUnit {

	@Test
	public void shouldFindAllPersons() throws Exception {
		LdapContext ctx = (LdapContext) ServerIntegrationUtils.getWiredContext(ldapServer, null)
				.lookup("ou=Users,dc=example,dc=com");

		// we want a sorted result, based on the canonical name
		ctx.setRequestControls(new Control[] { new SortControl("cn", Control.CRITICAL) });

		NamingEnumeration<SearchResult> res = ctx.search("", "(objectClass=person)", new SearchControls());
		assertThat(res.hasMore(), equalTo(true));

		assertThat(res.next().getName(), equalTo("cn=John Steinbeck"));
		assertThat(res.next().getName(), equalTo("cn=Micha Kops"));
		assertThat(res.next().getName(), equalTo("cn=Santa Claus"));

	}
}