export interface Horse {
  id: number;
  name: string;
  description: string;
  birthDay: string;
  sex: string;
  favoriteSportId: number;
  motherId: number;
  fatherId: number;
}

export const emptyHorse = (): Horse => ({
  id: null,
  name: null,
  description: null,
  birthDay: null,
  sex: null,
  favoriteSportId: null,
  motherId: null,
  fatherId: null,
});

export const createHorse = <T extends Partial<Horse>>(
  initialValues: T
): Horse & T => {
  return Object.assign(emptyHorse(), initialValues);
};
