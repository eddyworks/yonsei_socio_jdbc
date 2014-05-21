use Twitter_20110101;

select u.name, m.messages, m.message_time from twitterUser u, messageTable5 m where u.userid = m.userid limit 3;