# D'oh!

Java client for Dokku.

## Example

```java
import io.deffun.ssh.exec.io.deffun.doh.JSchDokkuExecutor;
import io.deffun.ssh.exec.io.deffun.doh.SshDokkuExecutor;

SshDokkuExecutor sshDokkuExecutor = new JSchDokkuExecutor(jSch, dokkuUser, dokkuHost);
sshDokkuExecutor.session(dokku -> {
    dokku.apps().create("myapp");
    dokku.mariaDbPlugin().create("myappdb");
    dokku.mariaDbPlugin().link("myappdb", "myapp");
});
```
