interface UserState {
  currentUser: User | null;
  error: string | null;
  loading: boolean;
}