# Glucode Interview Assignment

Hi there! 

One of our developers, Steve, fell sick in the middle of completing a feature. We need your help to finish things off.

Steve was tasked with building a way to display some interesting facts about our engineers when he fell sick. He was able to finish the list of questions (with some bugs) but never got to implementing the profile view or finishing the ordering.

That's where you come in.
For the next **72 hours** you will have free reign to finish off what Steve started, any commits after that will be ignored.

# Overview
- You are free to change whatever you need.
- You will have 72 hours (3 days) within which you can make changes to the codebase.
- Below you will find a list of main tasks, with their detail further below.

### Main tasks
- [x] There are a total of **3 known** bugs, ranging from simple to complex. Fix the bugs.
- [x] Create and display a reusable component, which could be used for the profile view.
- [x] Finish implementing the mechanism to order the list of engineers and add tests.

> You can complete these tasks in any order you like, but starting with one or two bugs might be a good way to get into the codebase.

# Detail
## Bugs
For reference to the original design check out the ***original_design.png*** file in the ***assets*** folder. 
- [x] Bug 1: On the about screen, there seems to be a layout issue with the question.
- [x] Bug 2: The preselected answer is not visible. 
- [x] Bug 3: When changing the answer to a question no selection is visible. 
- [x] Extra Bugfix: Changing the answer does not persist for the question view

## Profile View
For designs check out the ***profile_view_designs*** folder in ***assets***.
- [x] Choose which version of the profile view design you would like to attempt. - Starting with Simple
- [x] Create a generic, reusable version of your chosen design.
- [x] Display your view at the top of the About screen.
- [x] Tapping on the image in your view should allow a user to select a new image to display from their gallery.
- [x] If a user updates their profile image, their image on the list of Engineers screen should also update.
- [x] Extra Bugfix: List of Engineers list item does not update due to tinting
- [x] Extra: Updated to the Standard Design layout with the QuickStats - reused by the Engineer List
- [x] Request for permission to access storage and images - handle permission responses the list of Engineers

## List of Engineers View
There are no designs for this task.
- [x] Order the list of engineers ascending based on the number of years, coffees or bugs.
- [x] Add tests
- [ ] Testing Bug: Testing needs more accuracy for filtering and ordering - additional assertions needed

## Additional Improvements and Refactors for Future
Here are some of my proposals on how to improve the app further
- [ ] Refactor - Update the app support for API 34 - allowing use of latest Android Features
- [ ] Refactor - With API 34 - Intent based Image Picker can be updated to use the new PhotoPicker
- [ ] Refactor - Introduce Jetpack Compose - redesign views with compose to improve testability
- [ ] Refactor - Move all logic implementations to MVVM architecture to improve testability
- [ ] Feature - Introduce Firebase to manage the dataset in MockData.kt - create cloud storage integration