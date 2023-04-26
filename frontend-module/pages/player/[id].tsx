import { MatchTable } from '@/components/MatchTable';
import { useGetPlayerById } from '@/util/hooks/useGetPlayerById';
import { Result, Typography } from 'antd';
import { useRouter } from 'next/router';

const PlayerView = () => {
  const router = useRouter();
  const { id } = router.query;
  const { playerById, isPlayerByIdLoading } = useGetPlayerById(id as string);

  if (isPlayerByIdLoading) return null;
  if (!playerById && !isPlayerByIdLoading) return <Result status="error" title="Spieler wurde nicht gefunden" />;

  return (
    <>
      <Typography.Title>{playerById?.name}</Typography.Title>
      <MatchTable matches={playerById?.matches || []} />
    </>
  );
};

export default PlayerView;
