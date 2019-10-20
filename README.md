# Lifecycle Flow

[![](https://action-badges.now.sh/ashdavies/lifecycle-flow)](https://github.com/ashdavies/lifecycle-flow/actions)
[![](https://img.shields.io/codacy/coverage/03ae86d9ce934421879bc407aa157732.svg)](https://app.codacy.com/project/ash.davies/lifecycle-flow/dashboard)
[![](https://img.shields.io/maven-central/v/io.io.ashdavies.lifecycle/lifecycle.svg)](https://search.maven.org/artifact/io.io.ashdavies.lifecycle/lifecycle)
![](https://img.shields.io/github/license/io.ashdavies/lifecycle-flow.svg)

[![](https://img.shields.io/codacy/grade/03ae86d9ce934421879bc407aa157732.svg)](https://app.codacy.com/project/ash.davies/lifecycle-flow/dashboard)
[![](https://img.shields.io/github/last-commit/io.ashdavies/lifecycle-flow.svg)](https://github.com/io.ashdavies/lifecycle-flow/commits/master)
[![](https://img.shields.io/github/issues-pr/io.ashdavies/lifecycle-flow.svg)](https://github.com/io.ashdavies/lifecycle-flow/pulls)

**Lifecycle Flow**

Lifecycle flow is a lean set of behaviours for use with Android JetPack Lifecycle components with a focus on architectural construction.
It's core is was to facilitate navigation between screens by providing a communication channel between ViewModel and LifecycleOwner.
Additionally, it strives to be composable and to make full use of Kotlin delegation patterns to allow the consumer to manage state effectively.

Earlier paradigms had included the use of a `SingleLiveData` or `SingleLiveEvent` to ensure events would not be retransmitted after configuration change.
However, to better allow for composability, the use of a typed `Event` is preferred to allow the consumer to choose how to handle the event.
Further information can be found [here](https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150)

Lifecycle Flow, can be broken down into three fundamental components.
- Lifecycle navigation communication through `Event` transmission
- Transformation of LiveData and scoped composable functions
- Stateful operations for management of state in a `ViewModel` (Experimental)

Over time, transformation functions will be replaced with their androidx counterpart once they are deemed stable.
Additionally state management operations should be considered experimental and not included in any public API.
