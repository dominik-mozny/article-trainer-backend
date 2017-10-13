Documentation for Article trainer - backend


Some concepts:

Minimum amount of questions for learning must be 4 times number of simultaneously questions.

How to fetch random question?
- Questions are sorted ascending according to time when they were last fetched.
- First 2 times number of simultaneously questions are taken eg. 20 for 10 simultaneously questions.
- Questions with smallest nuber of answers are taken
- From those questions one is randomly taken.
- Fetch date of randomly taken question is updated.